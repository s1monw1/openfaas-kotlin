project.group = "com.openfaas.kotlin.template"

plugins {
    kotlin("jvm") version "1.3.61"
    id("com.github.johnrengelman.shadow") version "5.1.0"
    application
}

repositories {
    jcenter()
    maven { setUrl("https://jitpack.io") }
}

dependencies {
    implementation("com.github.s1monw1:openfaas-kotlin-model:1.0.0")
    implementation("com.openfaas.kotlin.template:function")
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.5.1")
    testImplementation("org.assertj:assertj-core:3.11.1")
}

application {
    mainClassName = "com.openfaas.entrypoint.AppKt"
}


tasks.withType<Test> {
    useJUnitPlatform()
}