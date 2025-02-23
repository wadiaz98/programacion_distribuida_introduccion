plugins {
    id("java")
    id("application")
    id("io.freefair.lombok") version "8.11"
}

group = "com.distribuida"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

application {
    mainClass.set("com.distribuida.Main")
}

var helidonVersion = "4.1.6"

dependencies {

    implementation(platform("io.helidon:helidon-dependencies:${helidonVersion}"))

    // Actualiza las dependencias a la versión correspondiente
    implementation("io.helidon.integrations.cdi:helidon-integrations-cdi-datasource-hikaricp:${helidonVersion}")
    implementation("io.helidon.microprofile.server:helidon-microprofile-server:${helidonVersion}")
    implementation("org.glassfish.jersey.media:jersey-media-json-binding:3.0.0")

    runtimeOnly("jakarta.persistence:jakarta.persistence-api:2.2.3")
    runtimeOnly("jakarta.transaction:jakarta.transaction-api:1.3.3")

    runtimeOnly("io.helidon.integrations.cdi:helidon-integrations-cdi-datasource-hikaricp:${helidonVersion}")
    runtimeOnly("io.helidon.integrations.cdi:helidon-integrations-cdi-hibernate:${helidonVersion}")
    runtimeOnly("io.helidon.integrations.cdi:helidon-integrations-cdi-jta-weld:${helidonVersion}")
    runtimeOnly("io.helidon.integrations.cdi:helidon-integrations-cdi-jpa:${helidonVersion}")

    implementation("org.postgresql:postgresql:42.7.2")
    implementation("org.hibernate:hibernate-core:6.4.4.Final")

    implementation("com.ecwid.consul:consul-api:1.4.0")


    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("org.slf4j:slf4j-simple:2.0.9")
    runtimeOnly("io.helidon.logging:helidon-logging-jul")


    implementation("io.helidon.microprofile:helidon-microprofile-cors")
    implementation("org.jboss:jandex:3.1.6")


    implementation("org.eclipse.microprofile.health:microprofile-health-api:4.0")
    implementation("io.helidon.microprofile.health:helidon-microprofile-health:${helidonVersion}")

    // https://mvnrepository.com/artifact/io.helidon.health/helidon-health
    implementation("io.helidon.health:helidon-health:4.1.6")

    implementation("io.helidon.health:helidon-health:${helidonVersion}")
    // https://mvnrepository.com/artifact/io.helidon.health/helidon-health-checks
    implementation("io.helidon.health:helidon-health-checks:4.1.6")

    // https://mvnrepository.com/artifact/io.helidon.fault-tolerance/helidon-fault-tolerance
    implementation("io.helidon.fault-tolerance:helidon-fault-tolerance:4.1.6")
}



tasks.test {
    useJUnitPlatform()
}

sourceSets {
    main {
        output.setResourcesDir( file("${buildDir}/classes/java/main") )
    }
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes(
            mapOf("Main-Class" to "com.programacion.distribuida.authors.Main",
                "Class-Path" to configurations.runtimeClasspath
                    .get()
                    .joinToString(separator = " ") { file ->
                        "${file.name}"
                    })
        )
    }
    configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
}


tasks.distTar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
}

tasks.distZip {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
}

tasks.installDist{
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
}

tasks.named<JavaExec>("run") {
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.programacion.distribuida.authors.Main")
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



