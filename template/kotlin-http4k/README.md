## Template: kotlin-http4k

The Kotlin template uses gradle as a build system and http4k as a lightweight HTTP toolkit.

Gradle version: 5.5.1

#### Http4k

Learn about http4k [here](https://www.http4k.org/).

### Structure

There are two projects which make up a single gradle build:

- `function` - (Library) your function code as a developer, you will only ever see this folder
- `http4k-entrypoint` - (App) HTTP server and routing


### Handler

The handler is written in the `./src/main/com/openfaas/function` folder

Tests are supported via junit within `./src/test`

### External dependencies

External dependencies can be specified in `./build.gradle.kts`. 

If you want to integrate with another system via HTTP, you can, for instance, consider using the [http4k client libraries](https://www.http4k.org/guide/modules/clients/).
