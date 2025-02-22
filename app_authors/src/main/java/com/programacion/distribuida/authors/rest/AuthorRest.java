package com.programacion.distribuida.authors.rest;

import com.programacion.distribuida.authors.db.Author;
import com.programacion.distribuida.authors.repo.AuthorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path( "/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
@ApplicationScoped
public class AuthorRest {

    @Inject
    private AuthorRepository repository;


    @GET
    public Response findAll() {
        return Response.ok(repository.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        var author = repository.findById(id);
        System.out.println("Consultando el autor con id: " + id);
        return author != null ? Response.ok(author).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id,@Valid Author author) {
        var authorObj = repository.findById(id);
        if (authorObj == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        author.setId(authorObj.getId());
        repository.update(author);
        return Response.ok(author).build();
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        var author = repository.findById(id);
        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        repository.delete(author);
        return Response.ok("Deleted author with id: " + id).build();
    }

    @POST
    public Response create(@Valid Author author) {
        repository.save(author);
        return Response.ok(author).build();
    }


}
