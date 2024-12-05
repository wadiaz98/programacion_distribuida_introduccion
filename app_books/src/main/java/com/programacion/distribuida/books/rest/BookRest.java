package com.programacion.distribuida.books.rest;

import com.programacion.distribuida.books.db.Book;
import com.programacion.distribuida.books.repo.BookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class BookRest {

    @Inject
    BookRepository repository;

    @GET
    public List<Book> findAll() {
        return repository.findAll().list();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        var obj = repository.findByIdOptional(id);

        if(obj.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(obj.get()).build();
        }
    }
}
