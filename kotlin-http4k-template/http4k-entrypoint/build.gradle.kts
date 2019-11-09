plugins {
    kotlin("jvm") version "1.3.50"
    id("com.github.johnrengelman.shadow") version "5.1.0"
    application
}

repositories {
    jcenter()
}

project.group = "com.openfaas.kotlin.template"

fun junit(module: String) = "org.junit.jupiter:junit-jupiter-$module:5.5.1"
dependencies {
    implementation("com.openfaas.kotlin.template:function")
    implementation("org.http4k:http4k-core:3.194.0")

    testImplementation(junit("engine"))
    testImplementation("org.assertj:assertj-core:3.11.1")
}

application {
    // Define the main class for the application.
    mainClassName = "com.openfaas.entrypoint.AppKt"
}


tasks.withType<Test> {
    useJUnitPlatform()
}