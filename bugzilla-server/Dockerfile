# Dockerfile for Bugzilla with XML import support
FROM debian:bullseye-slim

# Set environment variables
ENV DEBIAN_FRONTEND=noninteractive
ENV BUGZILLA_VERSION=5.2
ENV BUGZILLA_USER=bugzilla
ENV BUGZILLA_ROOT=/var/www/html/bugzilla

# Install system dependencies
RUN apt-get update && apt-get install -y \
    apache2 \
    libapache2-mod-perl2 \
    build-essential \
    git \
    mariadb-client \
    libmariadb-dev \
    libmariadb-dev-compat \
    pkg-config \
    wget \
    curl \
    cpanminus \
    libdbi-perl \
    libtemplate-perl \
    libmime-tools-perl \
    libtimedate-perl \
    libdatetime-perl \
    libdatetime-timezone-perl \
    libdbd-mysql-perl \
    libcgi-pm-perl \
    libmath-random-isaac-perl \
    libemail-address-perl \
    libemail-mime-perl \
    liburi-perl \
    liblist-moreutils-perl \
    libmath-random-isaac-xs-perl \
    libapache2-mod-perl2-dev \
    libchart-perl \
    libxml-twig-perl \
    libgd-graph-perl \
    libtemplate-plugin-gd-perl \
    libsoap-lite-perl \
    libhtml-scrubber-perl \
    libjson-rpc-perl \
    libdaemon-generic-perl \
    libtheschwartz-perl \
    libtest-taint-perl \
    libauthen-radius-perl \
    libnet-ldap-perl \
    libauthen-sasl-perl \
    xmlto \
    lynx \
    && rm -rf /var/lib/apt/lists/*

# Create bugzilla user
RUN useradd -m -s /bin/bash $BUGZILLA_USER

# Download and extract Bugzilla
WORKDIR /tmp
RUN wget https://ftp.mozilla.org/pub/webtools/bugzilla/5.2-branch/bugzilla-5.2.tar.gz \
    && tar -xzf bugzilla-5.2.tar.gz \
    && mv bugzilla-5.2 $BUGZILLA_ROOT \
    && chown -R $BUGZILLA_USER:$BUGZILLA_USER $BUGZILLA_ROOT

# Configure Apache
COPY apache-bugzilla.conf /etc/apache2/sites-available/bugzilla.conf
RUN a2ensite bugzilla && a2dissite 000-default
RUN a2enmod cgi rewrite headers expires

# Install Perl dependencies
WORKDIR $BUGZILLA_ROOT
RUN ./install-module.pl --all
# Install DBD::MariaDB specifically for MariaDB connections
RUN cpanm DBD::MariaDB

# Copy configuration and scripts
COPY localconfig $BUGZILLA_ROOT/localconfig
COPY answers $BUGZILLA_ROOT/answers
COPY import-xml.pl $BUGZILLA_ROOT/import-xml.pl
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
RUN chmod +x $BUGZILLA_ROOT/import-xml.pl

# Set permissions
RUN chown -R $BUGZILLA_USER:www-data $BUGZILLA_ROOT
RUN chmod -R g+w $BUGZILLA_ROOT

# Expose port
EXPOSE 80

# Set entrypoint
ENTRYPOINT ["/entrypoint.sh"]
