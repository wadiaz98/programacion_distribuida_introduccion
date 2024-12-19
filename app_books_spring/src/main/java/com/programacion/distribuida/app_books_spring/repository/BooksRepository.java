package com.programacion.distribuida.app_books_spring.repository;

import com.programacion.distribuida.app_books_spring.db.Books;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class BooksRepository {

    @PersistenceContext
    EntityManager manager;

    public void save(Books book) {
        manager.persist(book);
    }

    public Books findById(Integer id) {
        return manager.find(Books.class, id);
    }

    public void update(Books book) {
        manager.merge(book);
    }

    public void delete(Books books) {
        manager.remove(books);
    }

    public List<Books> findAll() {
        return manager.createQuery("SELECT b FROM Books b", Books.class).getResultList();
    }

}
