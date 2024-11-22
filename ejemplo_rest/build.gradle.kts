plugins {
    id("java")
    id("application")
}

group = "com.programacion.distribuida"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/io.helidon.webserver/helidon-webserver
    implementation("io.helidon.webserver:helidon-webserver:4.1.4")
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.10.1")

}
