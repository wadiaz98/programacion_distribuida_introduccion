package com.programacion.distribuida;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.programacion.distribuida.db.Persona;
import com.programacion.distribuida.servicios.ServicioPersona;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;


public class PrincipalRest {

    public static void insert(ServerRequest req, ServerResponse res) {
        var servicio = CDI.current().select(ServicioPersona.class).get();

        String jsonBody = req.content().as(String.class);


        Gson gson = new Gson();
        Persona persona = gson.fromJson(jsonBody, Persona.class);

        System.out.println(persona);
        servicio.insertar(persona);

        // Responder con un mensaje de éxito
        res.send("Persona insertada exitosamente\n" + persona);
    }

    public static void searchByID(ServerRequest req, ServerResponse res) {

        Integer idParam = Integer.parseInt(req.path().pathParameters().get("id"));
        var servicio = CDI.current().select(ServicioPersona.class).get();

        var persona = servicio.findById(idParam);

        if (persona != null) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            String json = gson.toJson(persona);

            res.send(json);
        } else {

            res.status(404).send("{\"error\": \"Persona no encontrada\"}");
        }
    }

    public static void update(ServerRequest req, ServerResponse res) {
        var servicio = CDI.current().select(ServicioPersona.class).get();
        Integer idParam = Integer.parseInt(req.path().pathParameters().get("id"));
        String jsonBody = req.content().as(String.class);
        Persona p =servicio.findById(idParam);
        Gson gson = new Gson();
        Persona persona = gson.fromJson(jsonBody, Persona.class);

        if (p != null) {
            p.setNombre(persona.getNombre());
            p.setDireccion(persona.getDireccion());
            Persona updt = servicio.actualizar(p);
            res.send("Persona actualizada exitosamente\n" + updt);
        }else{
            res.status(404).send("{\"error\": \"Persona no encontrada\"}");
        }
    }

    public static void delete(ServerRequest req, ServerResponse res) {
        var servicio = CDI.current().select(ServicioPersona.class).get();
        Integer idParam = Integer.parseInt(req.path().pathParameters().get("id"));
        servicio.deleteById(idParam);
        res.send("Persona eliminada exitosamente");
    }


    public static void main(String[] args) {

    SeContainer container = SeContainerInitializer.newInstance()
            .initialize();

        /*HttpRouting routing = HttpRouting.builder()
                .get("/hello", (req, res) -> res.send("Hello World"))
        .build();*/

        WebServer.builder()
                        .routing(it -> it
                                .post("/persona", PrincipalRest::insert)
                                .get("/persona/{id}", PrincipalRest::searchByID)
                                .put("/persona/{id}", PrincipalRest::update)
                                .delete("/persona/{id}",PrincipalRest::delete))
                                .port(8080)
                                        .build()
                                                .start();
    }


}
