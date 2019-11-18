## Template: kotlin

The Kotlin template uses gradle as a build system.

Gradle version: 5.5.1

### Structure

There are two projects which make up a single gradle build:

- `function` - (Library) your function code as a developer, you will only ever see this folder
- `entrypoint` - (App) HTTP server and routing

### Handler

The handler is written in the `./src/main/com/openfaas/function` folder

Tests are supported via junit within `./src/test`

### External dependencies

External dependencies can be specified in ./build.gradle.kts
