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

dependencies {

    //HTTP
    // https://mvnrepository.com/artifact/io.helidon.webserver/helidon-webserver
    implementation("io.helidon.webserver:helidon-webserver:4.1.4")
     //JSOn
     // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.10.1")

    //cdi
    // https://mvnrepository.com/artifact/org.jboss.weld.se/weld-se-core
    implementation("org.jboss.weld.se:weld-se-core:5.1.3.Final")

    //jpa
    // https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core
    implementation("org.hibernate.orm:hibernate-core:6.6.3.Final")
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.7.4")

}


sourceSets {
    main {
        output.setResourcesDir( file("${buildDir}/classes/java/main") )
    }
}
