package com.programacion.distribuida.authors.repo;


import com.programacion.distribuida.authors.db.Author;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


import java.util.List;


@ApplicationScoped
@Transactional
public class AuthorRepository {

    @PersistenceContext
    private EntityManager manager;

    public void save(Author author) {
        manager.persist(author);
    }
    public Author findById(Integer id) {
        return manager.find(Author.class, id);
    }
    public void update(Author author) {
        manager.merge(author);
    }
    public void delete(Author author) {
        manager.remove(author);
    }
    public List<Author> findAll() {
        return manager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }
}
