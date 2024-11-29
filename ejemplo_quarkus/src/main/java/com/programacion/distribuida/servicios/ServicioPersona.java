package com.programacion.distribuida.servicios;

import com.programacion.distribuida.db.Persona;

import java.util.List;
import java.util.Optional;

public interface ServicioPersona {

    public void insertar(Persona persona);
    public Optional<Persona> findById(Integer id);
    public Persona actualizar(Persona persona);
    public void deleteById(Integer id);
    public List<Persona> findAll();
}
