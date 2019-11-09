## Template: kotlin-http4k-template

The Kotlin template uses gradle as a build system and http4k as a lightweight HTTP toolkit.

Gradle version: 5.5.1

### Structure

There are three projects which make up a single gradle build:

- `function` - (Library) your function code as a developer, you will only ever see this folder
- `http4k-entrypoint` - (App) HTTP server and routing


### Handler

The handler is written in the `./src/main/com/openfaas/function` folder

Tests are supported via junit within `./src/test`

### External dependencies

External dependencies can be specified in `./build.gradle.kts`
