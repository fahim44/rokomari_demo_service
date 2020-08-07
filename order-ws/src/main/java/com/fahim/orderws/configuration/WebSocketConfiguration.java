package com.fahim.orderws.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    private Environment environment;

    @Autowired
    public WebSocketConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/orders")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableStompBrokerRelay("/queue", "/topic")
                .setAutoStartup(true)
                .setRelayHost(environment.getProperty("spring.rabbitmq.host"))
                .setRelayPort(Integer.parseInt(environment.getProperty("app.rabbitmq.stomp.broker.port")))
                .setSystemLogin(environment.getProperty("spring.rabbitmq.username"))
                .setSystemPasscode(environment.getProperty("spring.rabbitmq.password"))
                .setClientLogin(environment.getProperty("spring.rabbitmq.username"))
                .setClientPasscode(environment.getProperty("spring.rabbitmq.password"));
    }
}
