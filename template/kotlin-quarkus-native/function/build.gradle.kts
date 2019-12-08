import io.quarkus.gradle.tasks.QuarkusDev
import io.quarkus.gradle.tasks.QuarkusNative
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.openfaas"

plugins {
    java
    id("io.quarkus") version "1.0.1.Final"

    kotlin("jvm") version "1.3.61"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.61"
}

repositories {
    mavenCentral()
}

fun org.gradle.kotlin.dsl.DependencyHandlerScope.quarkus(module: String) =
    "io.quarkus:quarkus-$module"

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation(enforcedPlatform("io.quarkus:quarkus-bom:1.0.1.Final"))
    implementation(quarkus("kotlin"))
    implementation(quarkus("resteasy"))
    implementation(quarkus("resteasy-jsonb"))

    testImplementation(quarkus("junit5"))
    testImplementation("io.rest-assured:rest-assured")
}

tasks {
    withType<QuarkusNative> {
        isEnableHttpUrlHandler = true
    }
    withType<QuarkusDev> {
        setSourceDir("$projectDir/src/main/kotlin")
    }
    withType<KotlinCompile>{
        kotlinOptions.jvmTarget = "1.8"
    }
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
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}