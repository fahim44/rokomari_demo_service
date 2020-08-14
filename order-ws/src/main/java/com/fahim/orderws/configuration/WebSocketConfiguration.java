package com.fahim.orderws.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Objects;

@RequiredArgsConstructor
@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    private final Environment environment;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableStompBrokerRelay("/topic")
                .setRelayHost(Objects.requireNonNull(environment.getProperty("spring.rabbitmq.host")))
                .setRelayPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("app.rabbitmq.stomp.broker.port"))))
                .setSystemLogin(Objects.requireNonNull(environment.getProperty("spring.rabbitmq.username")))
                .setSystemPasscode(Objects.requireNonNull(environment.getProperty("spring.rabbitmq.password")))
                .setClientLogin(Objects.requireNonNull(environment.getProperty("spring.rabbitmq.username")))
                .setClientPasscode(Objects.requireNonNull(environment.getProperty("spring.rabbitmq.password")));
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                .setAllowedOrigins("*");
        registry.addEndpoint("/socket")
                .setAllowedOrigins("*")
                .withSockJS();
    }
}