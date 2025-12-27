#!/bin/bash
set -e

echo "Starting Bugzilla container..."

# Wait for MariaDB to be ready
echo "Waiting for MariaDB to be ready..."
while ! mysqladmin ping -h"$DB_HOST" -u"$DB_USER" -p"$DB_PASS" --silent; do
    echo "MariaDB not ready, waiting..."
    sleep 5
done

echo "MariaDB is ready!"

cd /var/www/html/bugzilla

# Substitute environment variables in localconfig at runtime
echo "Configuring localconfig with runtime environment variables..."
sed -i "s/\$db_host = \$ENV{'DB_HOST'} || '.*';/\$db_host = '$DB_HOST';/" localconfig
sed -i "s/\$db_name = \$ENV{'DB_NAME'} || '.*';/\$db_name = '$DB_NAME';/" localconfig
sed -i "s/\$db_user = \$ENV{'DB_USER'} || '.*';/\$db_user = '$DB_USER';/" localconfig
sed -i "s/\$db_pass = \$ENV{'DB_PASS'} || '.*';/\$db_pass = '$DB_PASS';/" localconfig
sed -i "s/\$site_wide_secret = \$ENV{'SITE_SECRET'} || '.*';/\$site_wide_secret = '$SITE_SECRET';/" localconfig

# Generate localconfig if it doesn't exist
if [ ! -f localconfig ]; then
    echo "Generating localconfig..."
    ./checksetup.pl --check-modules
fi

# Create answers file with environment variables
echo "Creating answers file..."
cat > /tmp/answers << EOF
# Bugzilla answers file for automated setup
\$answer{'ADMIN_EMAIL'} = '$ADMIN_EMAIL';
\$answer{'ADMIN_REALNAME'} = 'Bugzilla Administrator';
\$answer{'ADMIN_PASSWORD'} = '$ADMIN_PASSWORD';
\$answer{'create_htaccess'} = 1;
\$answer{'cvsbin'} = '';
\$answer{'diffpath'} = '/usr/bin/diff';
\$answer{'interdiffbin'} = '';
\$answer{'use_suexec'} = 0;
\$answer{'webservergroup'} = 'www-data';
\$answer{'db_driver'} = 'mysql';
\$answer{'db_host'} = '$DB_HOST';
\$answer{'db_name'} = '$DB_NAME';
\$answer{'db_user'} = '$DB_USER';
\$answer{'db_pass'} = '$DB_PASS';
\$answer{'db_port'} = 3306;
\$answer{'db_sock'} = '';
\$answer{'urlbase'} = '$BUGZILLA_URL';
\$answer{'ssl_redirect'} = 0;
\$answer{'NO_PAUSE'} = 1
EOF

# Run checksetup to initialize database
echo "Running checksetup.pl with answers file..."
./checksetup.pl /tmp/answers

# Import XML data if it exists and database is empty
if [ -f /import/bugs.xml ]; then
    echo "Checking if data needs to be imported..."
    BUG_COUNT=$(mysql -h"$DB_HOST" -u"$DB_USER" -p"$DB_PASS" -D"$DB_NAME" -se "SELECT COUNT(*) FROM bugs" 2>/dev/null || echo "0")
    
    # perl import-xml.pl

    if [ "$BUG_COUNT" = "0" ]; then
        echo "No bugs found, importing XML data..."
        perl import-xml.pl
        echo "XML import completed!"
    else
        echo "Database already contains $BUG_COUNT bugs, skipping import."
    fi
fi

# Set proper permissions
chown -R bugzilla:www-data /var/www/html/bugzilla
chmod -R g+w /var/www/html/bugzilla/data

# Start Apache in foreground
echo "Starting Apache..."
exec apache2ctl -D FOREGROUND
