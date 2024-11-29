package com.programacion.distribuida.servicios;

import com.programacion.distribuida.db.Persona;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
class ServicioPersonaImpl implements ServicioPersona {

    @Inject
    EntityManager em;

    @Override
    public void insertar(Persona persona) {
        em.getTransaction().begin();
        em.persist(persona);
        em.getTransaction().commit();
    }

    @Override
    public Optional<Persona> findById(Integer id) {
        return Optional.ofNullable(em.find(Persona.class, id));
    }

    @Override
    public Persona actualizar(Persona persona) {
        em.getTransaction().begin();
        Persona p = em.merge(persona);
        em.getTransaction().commit();
        return p;

    }

    @Override
    public void deleteById(Integer id) {
        em.getTransaction().begin();
        em.remove(this.findById(id));
        em.getTransaction().commit();
    }

    @Override
    public List<Persona> findAll() {
        var query = em.createQuery("select p from Persona p", Persona.class);
        return query.getResultList();
    }
}