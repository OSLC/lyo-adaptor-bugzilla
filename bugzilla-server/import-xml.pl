#!/usr/bin/perl
# Import XML data into Bugzilla database

use strict;
use warnings;
use lib qw(. lib local/lib/perl5);

use Bugzilla;
use Bugzilla::Constants;
use Bugzilla::User;
use Bugzilla::Product;
use Bugzilla::Component;
use Bugzilla::Bug;
use Bugzilla::Comment;
use Bugzilla::Install::Util qw(indicate_progress);
use XML::Twig;
use Data::Dumper;

# Initialize Bugzilla
Bugzilla->usage_mode(USAGE_MODE_CMDLINE);

# Mapping functions to convert XML values to valid Bugzilla values
sub map_operating_system {
    my ($os) = @_;
    return 'All' unless $os;
    
    # Map common OS values to Bugzilla defaults
    my %os_map = (
        'Mac OS X' => 'Mac OS',
        'Windows 7' => 'Windows',
        'Windows' => 'Windows',
        'Linux' => 'Linux',
        'All' => 'All',
    );
    
    return $os_map{$os} || 'Other';
}

sub map_priority {
    my ($priority) = @_;
    return 'Normal' unless $priority;  # Default to middle priority
    
    # Map priority values to valid Bugzilla priorities
    my %priority_map = (
        'P1' => 'Highest',
        'P2' => 'High',
        'P3' => 'Normal',
        'P4' => 'Low',
        'P5' => 'Lowest',
        'High' => 'High',
        'Normal' => 'Normal',
        'Low' => 'Low',
    );
    
    return $priority_map{$priority} || 'Normal';
}

sub map_severity {
    my ($severity) = @_;
    return 'normal' unless $severity;
    
    # Map severity values to valid Bugzilla severities
    my %severity_map = (
        'blocker' => 'blocker',
        'critical' => 'critical',
        'major' => 'major',
        'normal' => 'normal',
        'minor' => 'minor',
        'trivial' => 'trivial',
        'enhancement' => 'enhancement',
    );
    
    return $severity_map{lc($severity)} || 'normal';
}

sub map_platform {
    my ($platform) = @_;
    return 'All' unless $platform;
    
    # Map platform values to valid Bugzilla platforms
    my %platform_map = (
        'PC' => 'PC',
        'Macintosh' => 'Macintosh',
        'Sun' => 'Other',
        'All' => 'All',
    );
    
    return $platform_map{$platform} || 'All';
}

# Debug mode - set to 1 to only import 10 bugs for testing
my $debug_mode = 0;
my $max_bugs_debug = 10;

my $xml_file = '/import/bugs.xml';

unless (-f $xml_file) {
    die "XML file not found: $xml_file\n";
}

print "Starting XML import from: $xml_file\n";

# Create a parser
my $twig = XML::Twig->new(
    twig_handlers => {
        bug => \&process_bug,
    },
    pretty_print => 'indented',
);

my $bug_count = 0;
my $imported_count = 0;

sub process_bug {
    my ($twig, $bug_element) = @_;
    
    $bug_count++;
    
    # Debug mode: stop after max_bugs_debug
    if ($debug_mode && $bug_count > $max_bugs_debug) {
        print "Debug mode: stopping after $max_bugs_debug bugs\n";
        $twig->finish;
        return;
    }
    
    print "Processing bug $bug_count...\n" if $bug_count % 10 == 0 || $debug_mode;
    
    my $bug_data = extract_bug_data($bug_element);
      # Note: We're importing bugs with new IDs, not preserving original IDs
    # Original bug ID was: $bug_data->{bug_id}
    
    eval {
        import_bug($bug_data);
        $imported_count++;
    };
    
    if ($@) {
        print "Error importing bug $bug_data->{bug_id}: $@\n";
    }
    
    $twig->purge;  # Free memory
}

sub extract_bug_data {
    my ($bug_element) = @_;
    
    my $bug_data = {};
    
    # Extract basic bug information
    $bug_data->{bug_id} = get_element_text($bug_element, 'bug_id');
    $bug_data->{creation_ts} = get_element_text($bug_element, 'creation_ts');
    $bug_data->{short_desc} = get_element_text($bug_element, 'short_desc');
    $bug_data->{delta_ts} = get_element_text($bug_element, 'delta_ts');
    $bug_data->{product} = get_element_text($bug_element, 'product');
    $bug_data->{component} = get_element_text($bug_element, 'component');
    $bug_data->{version} = get_element_text($bug_element, 'version');
    $bug_data->{rep_platform} = get_element_text($bug_element, 'rep_platform');
    $bug_data->{op_sys} = get_element_text($bug_element, 'op_sys');
    $bug_data->{bug_status} = get_element_text($bug_element, 'bug_status');
    $bug_data->{resolution} = get_element_text($bug_element, 'resolution');
    $bug_data->{priority} = get_element_text($bug_element, 'priority');
    $bug_data->{bug_severity} = get_element_text($bug_element, 'bug_severity');
    $bug_data->{target_milestone} = get_element_text($bug_element, 'target_milestone');
    $bug_data->{reporter} = get_element_text($bug_element, 'reporter');
    $bug_data->{assigned_to} = get_element_text($bug_element, 'assigned_to');
    
    # Extract comments
    $bug_data->{comments} = [];
    my @comment_elements = $bug_element->children('long_desc');
    foreach my $comment (@comment_elements) {
        my $comment_data = {
            commentid => get_element_text($comment, 'commentid'),
            who => get_element_text($comment, 'who'),
            bug_when => get_element_text($comment, 'bug_when'),
            thetext => get_element_text($comment, 'thetext'),
        };
        push @{$bug_data->{comments}}, $comment_data;
    }
    
    return $bug_data;
}

sub get_element_text {
    my ($parent, $child_name) = @_;
    my $child = $parent->first_child($child_name);
    return $child ? $child->text : '';
}

sub import_bug {
    my ($bug_data) = @_;
      # Set the current user to admin for permissions
    my $admin = Bugzilla::User->new({ name => 'admin@bugzilla.local' });
    # if (!$admin) {
    #     # Fallback to any admin user
    #     my $dbh = Bugzilla->dbh;
    #     my $admin_id = $dbh->selectrow_array("SELECT userid FROM profiles WHERE login_name LIKE '%admin%' LIMIT 1");
    #     $admin = Bugzilla::User->new($admin_id) if $admin_id;
    # }
    Bugzilla->set_user($admin) if $admin;
    
    if ($debug_mode) {
        print "Importing bug: $bug_data->{bug_id}\n";
        print "  Product: $bug_data->{product}\n";
        print "  Component: $bug_data->{component}\n";
        print "  Reporter: $bug_data->{reporter}\n";
        print "  Assigned to: $bug_data->{assigned_to}\n";
        print "  Summary: $bug_data->{short_desc}\n";
    }
    
    # Ensure required users exist
    my $reporter = ensure_user_exists($bug_data->{reporter});
    my $assignee = ensure_user_exists($bug_data->{assigned_to});
      # Ensure product and component exist
    my $product = ensure_product_exists($bug_data->{product});
    my $component = ensure_component_exists($product, $bug_data->{component});
    
    # Ensure version exists
    # my $version = ensure_version_exists($product, $bug_data->{version});
        # Create the bug
    print "Creating bug with:\n";
    print "  product: " . $product->name . "\n";
    print "  component: " . $component->name . "\n";
    print "  reporter: " . $reporter->id . " (" . $reporter->login . ")\n";
    print "  assigned_to: " . $assignee->id . " (" . $assignee->login . ")\n";    # Map field values to valid Bugzilla values
    my $mapped_priority = map_priority($bug_data->{priority});  # Disabled for now
    my $mapped_severity = map_severity($bug_data->{bug_severity});
    my $mapped_platform = map_platform($bug_data->{rep_platform});
    my $mapped_os = map_operating_system($bug_data->{op_sys});
    
    if ($debug_mode) {
        print "  Mapped values:\n";
        print "    priority: $bug_data->{priority} -> $mapped_priority\n";
        print "    severity: $bug_data->{bug_severity} -> $mapped_severity\n";
        print "    platform: $bug_data->{rep_platform} -> $mapped_platform\n";
        print "    OS: $bug_data->{op_sys} -> $mapped_os\n";
    }
    
    my $bug = Bugzilla::Bug->create({
        product => $product->name,
        component => $component->name,
        # product => "TestProduct",  # Use a test product for debugging
        # component => "TestComponent",  # Use a test component for debugging
        short_desc => sprintf('[%d] %s', $bug_data->{bug_id}, $bug_data->{short_desc}),
        bug_status => 'CONFIRMED',  # Use a standard status instead of imported one
        # resolution => $bug_data->{resolution} || '',
        priority => $mapped_priority || 'Normal',  # Use original priority with fallback
        bug_severity => $mapped_severity,
        version => 'unspecified', #$version,
        rep_platform => $mapped_platform,
        op_sys => $mapped_os,
        reporter => $reporter->login,
        assigned_to => $assignee->login,
        # Note: creation_ts will be set automatically by Bugzilla
    });
    
    # Add comments
    foreach my $comment_data (@{$bug_data->{comments}}) {
        if ($comment_data->{thetext}) {
            my $comment_user = ensure_user_exists($comment_data->{who});
            $bug->add_comment($comment_data->{thetext}, {
                who => $comment_user,
                bug_when => $comment_data->{bug_when},
            });
        }
    }
    
    $bug->update();
    
    print "Imported bug " . $bug->id . " (original ID: $bug_data->{bug_id}): $bug_data->{short_desc}\n";
}

sub ensure_user_exists {
    my ($username) = @_;
    return unless $username;
    
    # Extract email if username contains name
    my $email = $username;
    if ($username =~ /^(.+?)\s+<(.+?)>$/) {
        $email = $2;
    } elsif ($username !~ /@/) {
        $email = "$username\@imported.bugzilla";
    }
    
    print "Looking for user: $username (email: $email)\n" if $debug_mode;
      my $user;
    
    # Try to find existing user first using check
    eval {
        $user = Bugzilla::User->check($email);
        print "Found existing user: $email (ID: " . $user->id . ")\n" if $debug_mode;
    };
    
    if ($@) {
        # User doesn't exist, create them
        print "User not found, creating new user: $username with email: $email\n" if $debug_mode;
        eval {
            $user = Bugzilla::User->create({
                login_name => $email,
                realname => $username,
                cryptpassword => '*',  # Disabled password
            });
            print "Successfully created user: $email (ID: " . $user->id . ")\n" if $debug_mode;
        };
        if ($@) {
            print "Error creating user $email: $@\n";
            die "Failed to create user: $@";
        }
    }
    
    return $user;
}

sub ensure_product_exists {
    my ($product_name) = @_;
    
    print "Looking for product: $product_name\n" if $debug_mode;
    
    # Try to find existing product first
    my $product = Bugzilla::Product->new({ name => $product_name });
    
    unless ($product) {
        print "Creating product: $product_name\n";
        eval {
            $product = Bugzilla::Product->create({
                name => $product_name,
                description => "Imported product: $product_name",
                # defaultmilestone => '---',
                version => 'unspecified',  # Default version
            });
            print "Successfully created product: $product_name (ID: " . $product->id . ")\n";
        };
        if ($@) {
            print "Error creating product $product_name: $@\n";
            die "Failed to create product: $@";
        }
    } else {
        print "Found existing product: $product_name (ID: " . $product->id . ")\n" if $debug_mode;
    }
    
    return $product;
}

sub ensure_version_exists {
    my ($product, $version_name) = @_;
    
    # Default version if none specified
    $version_name = 'unspecified' unless $version_name;
    
    # print "Looking for version: $version_name in product: " . $product->name . "\n" if $debug_mode;
    
    # # Check if version exists
    # my $dbh = Bugzilla->dbh;
    # my $version_id = $dbh->selectrow_array(
    #     "SELECT id FROM versions WHERE product_id = ? AND value = ?",
    #     undef, $product->id, $version_name
    # );
    
    # unless ($version_id) {
    #     print "Creating version: $version_name in product: " . $product->name . "\n";
    #     eval {
    #         $dbh->do(
    #             "INSERT INTO versions (value, product_id) VALUES (?, ?)",
    #             undef, $version_name, $product->id
    #         );
    #         print "Successfully created version: $version_name\n";
    #     };
    #     if ($@) {
    #         print "Error creating version $version_name: $@\n";
    #         # Fall back to using 'unspecified'
    #         $version_name = 'unspecified';
    #     }
    # } else {
    #     print "Found existing version: $version_name\n" if $debug_mode;
    # }
    
    return $version_name;
}

sub ensure_component_exists {
    my ($product, $component_name) = @_;
    
    # print "Looking for component: $component_name in product: " . $product->name . "\n" if $debug_mode;
    my $component = Bugzilla::Component->new({
        product => $product,
        name => $component_name
    });  
    unless ($component) {
        print "Creating component: $component_name in product: " . $product->name . "\n";
        # Get admin user for default assignee - use the first admin we can find
        my $admin;
        eval {
            $admin = Bugzilla::User->new({ name => 'admin@bugzilla.local' });
            # if (!$admin) {
            #     # Fallback to any admin user
            #     my $dbh = Bugzilla->dbh;
            #     my $admin_id = $dbh->selectrow_array("SELECT userid FROM profiles WHERE login_name LIKE '%admin%' LIMIT 1");
            #     $admin = Bugzilla::User->new($admin_id) if $admin_id;
            # }
        };
        
        unless ($admin) {
            die "Cannot find admin user for component creation";
        }
        
        print "Using admin user: " . $admin->login . " (ID: " . $admin->id . ") for component\n";
          eval {
            $component = Bugzilla::Component->create({
                name => $component_name,
                product => $product,
                description => "Imported component: $component_name",
                initialowner => $admin->login,  # Use login, not ID
            });
            print "Successfully created component: $component_name (ID: " . $component->id . ")\n";
        };
        if ($@) {
            print "Error creating component $component_name: $@\n";
            die "Failed to create component: $@";
        }
    } else {
        print "Found existing component: $component_name (ID: " . $component->id . ")\n" if $debug_mode;
    }
    
    return $component;
}

# Start processing
print "Parsing XML file...\n";
$twig->parsefile($xml_file);

print "\nImport complete!\n";
print "Total bugs processed: $bug_count\n";
print "Successfully imported: $imported_count\n";
print "Errors: " . ($bug_count - $imported_count) . "\n";
