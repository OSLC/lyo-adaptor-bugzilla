# Development

`adaptor-bugzilla/` is a Maven WAR project built with Maven 3.9+ and JDK 21 or 25.
Production classes target Java 8; JUnit 6 tests target Java 17. The packaged
application runs in Jetty 9 on Java 17 and uses Lyo 2.4.

Run the Java tests from the repository root:

```sh
mvn -B --no-transfer-progress -f adaptor-bugzilla/pom.xml test
```

`BugzillaLoginTest` exercises j2bugzilla's XML-RPC login against an embedded
MockServer, checks the returned ID/token and verifies the request method. It
requires no Docker or live Bugzilla. Test credentials are dummy values.

For the running stack, use the [README quickstart](README.md#getting-started).
`test/Bugzilla.postman_collection.json` exercises both HTTP services. Once the
stack is ready, use Node.js 24/npm to install the locked Newman dependency and
run it:

```sh
npm ci --prefix test --ignore-scripts
test/node_modules/.bin/newman run test/Bugzilla.postman_collection.json
```

The root Compose file builds the adaptor and `bugzilla-server/` backend. The
backend imports `bugzilla-server/show_bug.cgi.xml` when the database contains no
bugs. MariaDB and Bugzilla data are persisted in named volumes. Inspect startup
with `docker compose logs`; use `docker compose down` to stop the stack without
removing its data.

CI runs Maven and Postman checks on Java 21 and 25, including merge-queue commits.
The image workflow builds both Compose images and publishes them to GHCR on
trusted default-branch pushes or manual runs.
