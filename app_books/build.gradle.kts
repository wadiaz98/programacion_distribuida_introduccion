plugins {
    id("java")

    id("io.quarkus") version "3.17.0"
    id("io.freefair.lombok") version "8.11"

}

group = "com.programacion.distribuida"
version = "unspecified"

repositories {
    mavenCentral()
}
var quarkusVersion = "3.17.0"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:${quarkusVersion}"))

    //CDI
    implementation("io.quarkus:quarkus-arc")

    //REST
    implementation(("io.quarkus:quarkus-rest"))

    //JSON

    implementation(("io.quarkus:quarkus-rest-jsonb"))
    implementation(("io.quarkus:quarkus-rest-client"))
    implementation(("io.quarkus:quarkus-rest-client-jsonb"))

    //Registro
    implementation("io.smallrye.reactive:smallrye-mutiny-vertx-consul-client")

    //Balanceador de carga Stork
    implementation("io.smallrye.stork:stork-service-discovery-consul");
    //JPA
    implementation("io.quarkus:quarkus-hibernate-orm-panache")

    implementation("io.quarkus:quarkus-jdbc-postgresql")
    implementation("org.postgresql:postgresql:42.7.4")

    //Health
    implementation("io.quarkus:quarkus-smallrye-health")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}