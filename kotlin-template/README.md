## Template: kotlin-template

The Kotlin template uses gradle as a build system.

Gradle version: 5.5.1

### Structure

There are three projects which make up a single gradle build:

- `function` - (Library) your function code as a developer, you will only ever see this folder
- `entrypoint` - (App) HTTP server and routing
- `model` - (App) classes for parsing request/response

### Handler

The handler is written in the `./function/src/main/com/openfaas/function` folder

Tests are supported via junit within `./function/src/test`

### External dependencies

External dependencies can be specified in ./function/build.gradle.kts
