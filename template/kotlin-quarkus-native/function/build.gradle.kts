group = "com.openfaas"

plugins {
    java
    id("io.quarkus")

    kotlin("jvm") version "1.6.0"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.0"
}

repositories {
    mavenCentral()
}

fun org.gradle.kotlin.dsl.DependencyHandlerScope.quarkus(module: String) =
    "io.quarkus:quarkus-$module"

dependencies {
    //implementation(kotlin("stdlib-jdk11"))
    
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:2.5.0.Final"))
    implementation(quarkus("kotlin"))
    implementation(quarkus("resteasy"))
    implementation(quarkus("resteasy-jsonb"))

    testImplementation(quarkus("junit5"))
    testImplementation("io.rest-assured:rest-assured")
}

quarkus {
    setOutputDirectory("$projectDir/build/classes/kotlin/main")
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
