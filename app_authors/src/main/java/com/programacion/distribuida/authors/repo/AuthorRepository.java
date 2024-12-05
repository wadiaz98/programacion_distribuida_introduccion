package com.programacion.distribuida.authors.repo;

import com.programacion.distribuida.authors.db.Author;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;


@ApplicationScoped
@Transactional
public class AuthorRepository implements PanacheRepositoryBase<Author, Integer> {
}
