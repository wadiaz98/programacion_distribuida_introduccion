package com.programacion.distribuida.rest;

import com.programacion.distribuida.db.Persona;
import com.programacion.distribuida.servicios.PersonaRepository;
import com.programacion.distribuida.servicios.ServicioPersona;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;
import java.util.List;


@Path("/personas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonaRest {

    @Inject
    PersonaRepository repository;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id")Integer id){
        var obj = repository.findByIdOptional(id);

        if(obj.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.ok(obj.get()).build();
        }

    }

    @GET
    public List<Persona> findAll(){
        return  repository.findAll().stream().toList();
    }

    @POST
    public Response create(Persona obj){
        repository.persist(obj);
        return Response.ok(obj).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id")Integer id, Persona per) {
        var obj = repository.updatePersona(id, per);
        if (obj.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(obj.get()).build();
    }


    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id")Integer id){
        repository.deleteById(id);
    }
}
