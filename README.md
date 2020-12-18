# Experimental OSLC Bugzilla application

[![](https://img.shields.io/badge/project-Eclipse%20Lyo-blue?color=418eeb)](https://github.com/eclipse/lyo)
[![Build Status](https://semaphoreci.com/api/v1/berezovskyi/lyo-adaptor-bugzilla/branches/master/badge.svg)](https://semaphoreci.com/berezovskyi/lyo-adaptor-bugzilla)
[![Discourse users](https://img.shields.io/discourse/users?color=28bd84&server=https%3A%2F%2Fforum.open-services.net%2F)](https://forum.open-services.net/)

If you are looking for the OSLC tutorial on building an adaptor for Bugzilla, please refer to the tutorials under the [OSLC Developer Guide](https://oslc.github.io/developing-oslc-applications/tutorials.html).

This repository is an experimental variant that shows what [Eclipse Lyo Designer](https://oslc.github.io/developing-oslc-applications/eclipse_lyo/lyo-designer.html) can do.

This application uses the [OSLC4J] SDK to build up the adapter from Plain Old Java Objects and relatively simple JAX-RS web services.

## Getting started

1. Run Bugzilla: `docker run -p 80:80 --rm smarx008/bugzilla-dev-lyo`
1. Run the adaptor: `mvn clean jetty:run-exploded`. **If you run with `jetty:run` only, the container will silently fail to load.***
1. Navigate to http://localhost:8180/BugzillaAdaptor/
1. Use admin:password as credentials to log in. It will pass them to the Bugzilla server (as specified in `adaptor-bugzilla/src/main/webapp/WEB-INF/web.xml`).

