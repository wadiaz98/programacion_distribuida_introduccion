package com.programacion.distribuida;

import com.programacion.distribuida.servicios.StringService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Principal {

    public static void main(String[] args) {

        //Contenedor
        try(SeContainer container = SeContainerInitializer.newInstance()
                .initialize()){
            //Uso de Lookup
            StringService servicio = container.select(StringService.class).get();

            var ret = servicio.convert("Hola cdi");

            System.out.println(ret);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
