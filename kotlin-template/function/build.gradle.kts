plugins {
    kotlin("jvm") version "1.3.50"
}

repositories {
    jcenter()
}

project.group = "com.openfaas.kotlin.template"


fun junit(module: String) = "org.junit.jupiter:junit-jupiter-$module:5.5.1"
dependencies {
    implementation("com.openfaas.kotlin.template:model")
    implementation(kotlin("stdlib-jdk8"))

    testImplementation(junit("engine"))
    testImplementation("org.assertj:assertj-core:3.11.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}