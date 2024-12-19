package com.programacion.distribuida.spring_app_authors;

import io.vertx.core.Vertx;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.ext.consul.ServiceOptions;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.UUID;

@Component
public class AuthorsLifeCycles {
    @Value("${consul.host}")
    private String consulIp;

    @Value("${consul.port}")
    private Integer consulPort;

    @Value("${server.port}")
    private Integer appPort;

    private String serviceId;
    private ConsulClient client;
    private Vertx vertx;

    @PostConstruct
    public void init() throws Exception {
        System.out.println("Authors service is starting...");

        vertx = Vertx.vertx();
        client = ConsulClient.create(vertx,
                new ConsulClientOptions()
                        .setHost(consulIp)
                        .setPort(consulPort)
        );

        serviceId = UUID.randomUUID().toString();
        var ipAddress = InetAddress.getLocalHost();

        client.registerService(
                new ServiceOptions()
                        .setName("app-authors")
                        .setId(serviceId)
                        .setAddress(ipAddress.getHostAddress())
                        .setPort(appPort),
                res -> {
                    if (res.succeeded()) {
                        System.out.println("Service registered successfully");
                    } else {
                        System.out.println("Service registration failed: " + res.cause().getMessage());
                    }
                }
        );
    }

    @PreDestroy
    public void stop() {
        System.out.println("Authors service is stopping...");
        client.deregisterService(serviceId, res -> {
            if (res.succeeded()) {
                System.out.println("Service deregistered successfully");
            } else {
                System.out.println("Service deregistration failed: " + res.cause().getMessage());
            }
        });
        vertx.close();
    }
}
