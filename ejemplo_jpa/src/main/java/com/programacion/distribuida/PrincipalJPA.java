package com.programacion.distribuida;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.programacion.distribuida.db.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PrincipalJPA {

    public static void main(String[] args) {

        EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("pu-distribuida");

        EntityManager em = emf.createEntityManager();

        //Agregar
/*
        Persona p = new Persona();
        p.setDireccion("dir4");
        p.setNombre("nom4");

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
*/

        //Consulta
        //em.getTransaction().begin(); //Inicio la transaccion
        var query = em.createQuery("SELECT m FROM Persona m order by m.nombre asc", Persona.class);

        List<Persona> ret = query.getResultList();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        var jsonList = ret.stream().map(gson::toJson);

        jsonList.forEach(System.out::println);

       // em.getTransaction().commit();// Cierra la transaccion


        //Consulta por id
        //var persona1 = em.find(Persona.class, 1);
        //System.out.println(persona1);

        em.close();
        emf.close();
    }



}
