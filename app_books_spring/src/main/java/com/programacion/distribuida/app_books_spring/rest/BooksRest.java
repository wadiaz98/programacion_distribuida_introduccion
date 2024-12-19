package com.programacion.distribuida.app_books_spring.rest;

import com.programacion.distribuida.app_books_spring.clients.AuthorRestClient;
import com.programacion.distribuida.app_books_spring.db.Books;
import com.programacion.distribuida.app_books_spring.dto.AuthorDto;
import com.programacion.distribuida.app_books_spring.dto.BookDto;
import com.programacion.distribuida.app_books_spring.repository.BooksRepository;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/books")
public class BooksRest {

    @Autowired
    BooksRepository repository;

    @Autowired
    AuthorRestClient client;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDto>> findAll() {
        return ResponseEntity.ok(repository.findAll().stream().map(book -> {
            AuthorDto author = client.findById(book.getAuthorId());

            BookDto dto = new BookDto();
            dto.setId(book.getId());
            dto.setIsbn(book.getIsbn());
            dto.setTittle(book.getTittle());
            dto.setPrice(book.getPrice());
            dto.setAuthor(author.getName() + " " + author.getApellido());

            return dto;
        }).toList());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Books> findById(@PathVariable Integer id) {
        Books book = repository.findById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Books> save(@RequestBody Books book) {
        repository.save(book);
        return ResponseEntity.ok(book);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Books> update(@PathVariable Integer id, @RequestBody Books book) {
        Books bookDB = repository.findById(id);
        if (bookDB == null) {
            return ResponseEntity.notFound().build();
        }
        book.setId(id);
        repository.update(book);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Books> delete(@PathVariable Integer id) {
        Books book = repository.findById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(book);
        return ResponseEntity.ok(book);
    }

}
