project.group = "com.openfaas.kotlin.template"

plugins {
    kotlin("jvm") version "1.3.61"
    id("com.github.johnrengelman.shadow") version "5.1.0"
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.openfaas.kotlin.template:function")
    implementation("org.http4k:http4k-core:3.194.0")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.5.1")
    testImplementation("org.assertj:assertj-core:3.11.1")
}

application {
    mainClassName = "com.openfaas.entrypoint.AppKt"
}


tasks.withType<Test> {
    useJUnitPlatform()
}