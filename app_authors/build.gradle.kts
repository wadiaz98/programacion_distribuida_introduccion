plugins {
    id("java")
    id("application")
    id("io.freefair.lombok") version "8.11"
}

group = "com.programacion.distribuida"
version = "unspecified"

repositories {
    mavenCentral()
}

var helidonVersion = "4.1.6"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

application {
    mainClass.set("io.helidon.microprofile.cdi.Main")
}

dependencies {
    // Helidon
    implementation(enforcedPlatform("io.helidon:helidon-dependencies:${helidonVersion}"))
    implementation("io.helidon.microprofile.bundles:helidon-microprofile")
    implementation("org.glassfish.jersey.media:jersey-media-json-binding")
    runtimeOnly("io.helidon.logging:helidon-logging-jul")
    runtimeOnly("io.smallrye:jandex")
    runtimeOnly("jakarta.activation:jakarta.activation-api")

    // CDI - hikari
    runtimeOnly("io.helidon.integrations.cdi:helidon-integrations-cdi-hibernate")
    runtimeOnly("io.helidon.integrations.cdi:helidon-integrations-cdi-datasource-hikaricp")
    // POSTGRES
    implementation("org.postgresql:postgresql:42.7.4")

    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api")
    implementation("jakarta.inject:jakarta.inject-api")
    implementation("jakarta.ws.rs:jakarta.ws.rs-api")
    implementation("jakarta.persistence:jakarta.persistence-api")
    implementation("jakarta.transaction:jakarta.transaction-api")
    implementation("io.helidon.common:helidon-common")

    runtimeOnly("io.helidon.integrations.cdi:helidon-integrations-cdi-jta-weld")
    runtimeOnly("io.helidon.integrations.cdi:helidon-integrations-cdi-jpa")
    runtimeOnly("org.hibernate.validator:hibernate-validator")
    runtimeOnly("org.glassfish:jakarta.el")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api")


    implementation("org.jboss.weld.se:weld-se-core:6.0.1.Final")
    // Health
    implementation("io.helidon.microprofile.bundles:helidon-microprofile-core")
    implementation("io.helidon.microprofile.health:helidon-microprofile-health")

    // RestClient
    implementation("io.helidon.microprofile.rest-client:helidon-microprofile-rest-client")

    // Metrics
    implementation("io.helidon.microprofile.metrics:helidon-microprofile-metrics")

    // OPENAPI
    implementation("io.helidon.microprofile.openapi:helidon-microprofile-openapi")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

tasks.register<Copy>("copyLibs") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath)
    into("build/libs/libs")
}

tasks.named("assemble") {
    dependsOn("copyLibs")
}

tasks.named<Jar>("jar") {
    manifest {
        attributes(
            "Main-Class" to "io.helidon.microprofile.cdi.Main",
            "Class-Path" to configurations.runtimeClasspath.get().files.joinToString(" ") { "libs/${it.name}" }
        )
    }
}