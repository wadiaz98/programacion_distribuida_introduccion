package com.programacion.distribuida.authors.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AuthorDto {

    private Integer id;

    private String name;


    private String apellido;
}
