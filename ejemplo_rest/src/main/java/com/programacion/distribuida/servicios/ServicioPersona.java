package com.programacion.distribuida.servicios;

import com.programacion.distribuida.db.Persona;

import java.util.List;

public interface ServicioPersona {

    public void insertar(Persona persona);
    public Persona findById(Integer id);
    public Persona actualizar(Persona persona);
    public void deleteById(Integer id);
    public List<Persona> findAll();
}
