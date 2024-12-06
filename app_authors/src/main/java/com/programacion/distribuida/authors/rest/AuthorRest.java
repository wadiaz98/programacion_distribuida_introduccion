package com.programacion.distribuida.authors.rest;

import com.programacion.distribuida.authors.db.Author;
import com.programacion.distribuida.authors.repo.AuthorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class AuthorRest {
    @Inject
    AuthorRepository repository;

    @GET
    public List<Author> findAll() {
        return repository.findAll().list();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id){
        var obj = repository.findByIdOptional(id);

        if( obj.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.ok(obj.get()).build();
        }
    }

    @POST
    public Response create(Author author) {
        repository.persist(author);
        return Response.ok(author).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Author author) {
        var obj = repository.updateBook(id, author);

        if(obj.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.ok(obj.get()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id){
        repository.deleteById(id);
    }
}
