package com.fahim.gatewayservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RouteConfig {

    private Environment environment;

    @Autowired
    public RouteConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path(environment.getProperty("app.order.ws.url.path"))
                        .uri("lb://" + environment.getProperty("app.order.ws.name") + "/"))


                .route(r -> r.path(environment.getProperty("app.order.ws.socket.url.path"))
                        .uri("lb://" + environment.getProperty("app.order.ws.name") + "/"))
                .build();
    }
}