package com.programacion.distribuida;

import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

public class PrincipalRest {

    public static void handleHello(ServerRequest req, ServerResponse res){
        res.send("Hola");
    }

    public static void main(String[] args) {


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
