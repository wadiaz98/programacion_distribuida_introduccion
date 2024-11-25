plugins {
    id("java")
    id("io.freefair.lombok") version "8.11"
}

group = "com.programacion.distribuida"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core
    implementation("org.hibernate.orm:hibernate-core:6.6.3.Final")
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.7.4")
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.10.1")

}


