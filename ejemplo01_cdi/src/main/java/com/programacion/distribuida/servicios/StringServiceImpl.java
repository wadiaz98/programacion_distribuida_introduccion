package com.programacion.distribuida.servicios;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;

@ApplicationScoped
public class StringServiceImpl implements StringService{

    @Inject
    LogService log;

    //@PostConstruct
    //public void init(){
    //    log = CDI.current().select(LogService.class).get();
    //}

    @Override
    public String convert(String txt) {

        log.log("Converting <<" + txt + ">>");
        return txt.toUpperCase();
    }
}
