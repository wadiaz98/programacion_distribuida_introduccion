package com.programacion.distribuida.spring_app_authors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class SpringAppAuthorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAppAuthorsApplication.class, args);
    }

}
