package com.programacion.distribuida.spring_app_authors.repository;

import com.programacion.distribuida.spring_app_authors.db.Authors;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class AuthorsRepository {

    @PersistenceContext
    EntityManager manager;

    public void save(Authors author) {
        manager.persist(author);
    }

    public Authors findById(Integer id) {
        return manager.find(Authors.class, id);
    }

    public List<Authors> findAll() {
        return manager.createQuery("SELECT a FROM Authors a", Authors.class).getResultList();
    }

    public void update(Authors author) {
        manager.merge(author);
    }

    public void delete(Authors author) {
        manager.remove(author);
    }

}
