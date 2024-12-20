package com.programacion.distribuida.app_books_spring.clients;

import com.programacion.distribuida.app_books_spring.dto.AuthorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "app-authors-spring")
public interface AuthorRestClient {

    @GetMapping(path = "/authors/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    AuthorDto findById(@PathVariable("id") Integer id);
}
