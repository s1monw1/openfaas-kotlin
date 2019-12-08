## Template: kotlin-quarkus-native

The Kotlin template uses gradle as a build system and is based on Quarkus to compile to a native image. Quarkus uses GraalVM in the background.

Gradle version: 6.0.1

#### Quarkus

Learn about Quarkus [here](https://quarkus.io/).

### Handler

The handler is written in the `./src/main/com/openfaas/function` folder

Tests are supported via junit within `./src/test`

### External dependencies

External dependencies can be specified in `./build.gradle.kts`. 

### Dev mode

Quarkus comes with outstanding [Hot Replacement](https://quarkus.io/vision/developer-joy) capabilities in their Dev mode. Run the function with `./gradlew QuarkusDev` to enable the mode and enjoy great turnaround times when developing. 

