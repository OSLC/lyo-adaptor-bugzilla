# Bugzilla Docker Setup

This setup creates a read-only Bugzilla instance serving bugs from your XML export file.

## Directory Structure

All files for this Bugzilla setup are contained in this directory:
- `show_bug.cgi.xml` - Your original Bugzilla XML export
- `Dockerfile` - Main container definition for Bugzilla
- `docker-compose.yml` - Multi-container setup with MariaDB
- `apache-bugzilla.conf` - Apache configuration for Bugzilla
- `localconfig` - Bugzilla database configuration
- `import-xml.pl` - Perl script to import XML data
- `entrypoint.sh` - Container startup script
- `mysql-init/01-init.sql` - MariaDB initialization

## Quick Start

1. **Navigate to this directory:**
   ```bash
   cd bugzilla-from-lyo-archives
   ```

2. **Build and start the containers:**
   ```bash
   docker-compose up --build
   ```

2. **Access Bugzilla:**
   - URL: http://localhost:8080 (localhost only for security)
   - Admin user: admin@bugzilla.local
   - Admin password: admin123

3. **The import process:**
   - On first startup, the XML file will be automatically imported
   - This may take several minutes depending on the size of your export
   - Check logs with: `docker-compose logs -f bugzilla`

## Configuration

### Environment Variables

Edit `docker-compose.yml` to customize:

- `ADMIN_EMAIL` - Admin user email
- `ADMIN_PASSWORD` - Admin user password  
- `BUGZILLA_URL` - Base URL for Bugzilla
- `DB_*` - Database connection settings

### Making it Read-Only

To make the instance truly read-only, you can:

1. **Database level:** Grant only SELECT permissions to the Bugzilla user
2. **Application level:** Modify Bugzilla templates to hide edit forms
3. **Web server level:** Block POST/PUT requests via Apache rules

Example Apache rules to add to `apache-bugzilla.conf`:
```apache
# Block write operations for read-only mode
<LocationMatch "/(process_bug|post_bug|attachment|editusers|editproducts)">
    Require all denied
</LocationMatch>
```

## Data Import

The XML import script (`import-xml.pl`) will:

- Create missing users with disabled passwords
- Create missing products and components
- Import all bugs with their comments and metadata
- Skip bugs that already exist (safe to re-run)

## Troubleshooting

1. **Import fails:** Check the container logs for Perl/MariaDB errors
2. **Permission issues:** Ensure the XML file is readable by the container
3. **MariaDB connection:** Verify database credentials in docker-compose.yml
4. **Large imports:** Consider increasing MariaDB timeouts for very large XML files

## Schema Compatibility

Your XML export from Bugzilla 5.0.3 should import successfully into Bugzilla 5.2. The XML format is designed to be forward/backward compatible. The major schema changes in Bugzilla affect internal database structure, not the XML export/import format.

## Performance Tips

For large datasets:
- Increase MariaDB memory settings in docker-compose.yml
- Consider adding MariaDB indexes after import
- Use MariaDB's InnoDB buffer pool tuning
- Mount data volumes on fast storage

## Security Notes

This setup is intended for internal/development use. For production:
- Change all default passwords
- Use proper SSL certificates
- Implement proper authentication
- Review Apache security configuration
- Consider running behind a reverse proxy

## Reverse Proxy Setup

Since Bugzilla is bound to localhost only, you'll need a reverse proxy for external access.

### Nginx Example

```nginx
server {
    listen 80;
    server_name your-bugzilla-domain.com;
    
    location / {
        proxy_pass http://127.0.0.1:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        
        # For larger bug attachments
        client_max_body_size 50M;
    }
}
```

### Apache Example

```apache
<VirtualHost *:80>
    ServerName your-bugzilla-domain.com
    
    ProxyRequests Off
    ProxyPreserveHost On
    
    ProxyPass / http://127.0.0.1:8080/
    ProxyPassReverse / http://127.0.0.1:8080/
    
    # Security headers
    Header always set X-Frame-Options "SAMEORIGIN"
    Header always set X-Content-Type-Options "nosniff"
</VirtualHost>
```

### Traefik Example (Docker)

```yaml
version: '3.8'
services:
  traefik:
    image: traefik:v3.0
    command:
      - "--api.dashboard=true"
      - "--providers.docker=true"
      - "--entrypoints.web.address=:80"
    ports:
      - "80:80"
      - "8888:8080"  # Traefik dashboard
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock
    
  bugzilla:
    # ... your existing bugzilla config ...
    labels:
      - "traefik.enable=true"
      - "traefik.http.routers.bugzilla.rule=Host(`your-bugzilla-domain.com`)"
