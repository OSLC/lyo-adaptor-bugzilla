# Contributing

Open pull requests against the default branch. Include a description of the
changed behavior and the checks run; report unavailable container checks.
See [DEVELOPMENT.md](DEVELOPMENT.md) for test commands.

The Java files marked as generated contain `Start of user code` / `End of user
code` regions. Keep handwritten changes within those regions; change the
Designer model or generator for changes to generated boilerplate. This checkout
does not include the Designer model; obtain it before regenerating the source.

Keep the [README quickstart](README.md#getting-started) working when changing
ports, configuration or containers. Document developer-only test procedures in
DEVELOPMENT.md and component details beside the component.

Do not commit real credentials or service recordings containing credentials.
