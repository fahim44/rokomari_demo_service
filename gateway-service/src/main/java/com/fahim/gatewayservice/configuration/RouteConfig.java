package com.fahim.gatewayservice.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@RequiredArgsConstructor
@Configuration
public class RouteConfig {

    private final Environment environment;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path(environment.getProperty("app.order.ws.url.path"))
                        .uri("lb://" + environment.getProperty("app.order.ws.name") + "/"))

                .route(r -> r.path(environment.getProperty("app.order.ws.socket.url.path"))
                        .uri("lb://" + environment.getProperty("app.order.ws.name") + "/"))

                .route(r -> r.path(environment.getProperty("app.web.client.url.path"))
                        .uri("lb://" + environment.getProperty("app.web.client.name") + "/"))
                .build();
    }
}