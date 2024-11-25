package com.programacion.distribuida;

import com.programacion.distribuida.servicios.ServicioPersona;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class PrincipalRest {

    public static void handleHello(ServerRequest req, ServerResponse res){
        var servicio = CDI.current().select(ServicioPersona.class).get();



        servicio.findById(1);

        res.send("Hola");
    }

    public static void main(String[] args) {

    SeContainer container = SeContainerInitializer.newInstance()
            .initialize();

        HttpRouting routing = HttpRouting.builder()
                .get("/hello", (req, res) -> res.send("Hello World"))
        .build();

        WebServer.builder()
                        .routing(it -> it
                                .get("/hello", PrincipalRest::handleHello))
                                .port(8080)
                                        .build()
                                                .start();
    }


}
