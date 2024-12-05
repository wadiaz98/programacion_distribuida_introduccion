package com.programacion.distribuida.books.db;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String isbn;

    private String tittle;

    private BigDecimal price;

    @Column(name = "author_id")
    private Integer authorId;
}
