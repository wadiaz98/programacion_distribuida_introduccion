package com.programacion.distribuida.spring_app_authors.rest;


import com.programacion.distribuida.spring_app_authors.db.Authors;
import com.programacion.distribuida.spring_app_authors.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/authors")
public class AuthorsRest {

    @Autowired
    AuthorsRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Authors>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Authors> findById(@PathVariable Integer id) {
        Authors author = repository.findById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Authors> save(@RequestBody Authors author) {
        System.out.println("author = " + author);
        repository.save(author);
        return ResponseEntity.ok(author);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Authors> update(@PathVariable Integer id,@RequestBody Authors author) {
        Authors authorDB = repository.findById(id);
        if (authorDB == null) {
            return ResponseEntity.notFound().build();
        }
        author.setId(id);
        repository.update(author);
        return ResponseEntity.ok(author);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Authors> delete(@PathVariable Integer id) {
        Authors author = repository.findById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(author);
        return ResponseEntity.ok(author);
    }

}
