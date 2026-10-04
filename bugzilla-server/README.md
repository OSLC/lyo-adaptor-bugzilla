# Bugzilla backend

This image runs Bugzilla 5.2 with Apache and Perl. The root
[Compose file](../docker-compose.yml) supplies MariaDB, database settings and
administrator credentials. Use the [root quickstart](../README.md#getting-started)
to start it alongside the OSLC adaptor.

`entrypoint.sh` waits for MariaDB, runs `checksetup.pl`, and invokes
`import-xml.pl` when `/import/bugs.xml` exists and the database contains no bugs.
Compose mounts `show_bug.cgi.xml` at that path. The import creates missing users,
products and components and maps exported enum values to the target database.

`localconfig` and `answers` provide installation defaults;
`apache-bugzilla.conf` configures the HTTP server. Inspect the backend startup
with `docker compose logs bugzilla-server-app` from the repository root.
