package com.programacion.distribuida.authors.health;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.context.Destroyed;

@ApplicationScoped
public class ConsulService {
    private String serviceId;
    private ConsulClient consulClient;

    void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        String consulHost = System.getProperty("consul.host", "localhost");
        int consulPort = Integer.parseInt(System.getProperty("consul.port", "8500"));

        consulClient = new ConsulClient(consulHost, consulPort);
        serviceId = "app-authors-" + System.currentTimeMillis();

        NewService service = new NewService();
        service.setId(serviceId);
        service.setName("app-authors");
        service.setPort(9090);

        NewService.Check check = new NewService.Check();
        check.setHttp("http://localhost:9090/health");
        check.setInterval("10s");
        service.setCheck(check);

        consulClient.agentServiceRegister(service);
        System.out.println("Service registered in Consul: " + serviceId);
    }

    void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {
        if (consulClient != null && serviceId != null) {
            consulClient.agentServiceDeregister(serviceId);
            System.out.println("Service deregistered from Consul: " + serviceId);
        }
    }
}