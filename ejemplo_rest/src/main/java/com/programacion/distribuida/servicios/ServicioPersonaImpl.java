package com.programacion.distribuida.servicios;

import com.programacion.distribuida.db.Persona;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
class ServicioPersonaImpl implements  ServicioPersona{

    @Inject
    EntityManager em;

    @Override
    public Persona findById(Integer id) {
        return em.find(Persona.class, id);
    }
}
