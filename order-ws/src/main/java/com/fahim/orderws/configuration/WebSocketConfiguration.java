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
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // config.enableSimpleBroker("/topic");
        config.enableStompBrokerRelay("/topic")
                .setRelayHost(environment.getProperty("spring.rabbitmq.host"))
                .setRelayPort(Integer.parseInt(environment.getProperty("app.rabbitmq.stomp.broker.port")))
                .setSystemLogin(environment.getProperty("spring.rabbitmq.username"))
                .setSystemPasscode(environment.getProperty("spring.rabbitmq.password"))
                .setClientLogin(environment.getProperty("spring.rabbitmq.username"))
                .setClientPasscode(environment.getProperty("spring.rabbitmq.password"));
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

    /*private Environment environment;

    @Autowired
    public WebSocketConfiguration(Environment environment) {
        this.environment = environment;
    }

   *//* @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        RequestUpgradeStrategy upgradeStrategy = new TomcatRequestUpgradeStrategy();
        registry.addEndpoint("/orders")
                .setHandshakeHandler(new DummyHanshakeHandler())
                .addInterceptors(new DummyGatekeeper())
                .setAllowedOrigins("*");

        registry.addEndpoint("/orders")
                //.setHandshakeHandler(new DefaultHandshakeHandler(upgradeStrategy))
                .setHandshakeHandler(new DummyHanshakeHandler())
                .addInterceptors(new DummyGatekeeper())
                .setAllowedOrigins("*")
                .withSockJS();
                //.addInterceptors(new DummyGatekeeper())
               // .withSockJS();
    }*//*

    @Override
    protected void configureStompEndpoints(StompEndpointRegistry registry) {
//We send message to the process endpoint: ws://localhost:80/<app-name>/process OR /listen
        registry.addEndpoint("orders")
                .setHandshakeHandler(new DummyHanshakeHandler())
                .addInterceptors(new DummyGatekeeper()) //
                .setAllowedOrigins("*");

        //Enable SockJS fall back configuration.
        registry.addEndpoint("orders")
                .setHandshakeHandler(new DummyHanshakeHandler())
                .addInterceptors(new DummyGatekeeper()) //
                .setAllowedOrigins("*")
                .withSockJS();
    }

    *//*@Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        *//**//*registry.enableStompBrokerRelay("/queue", "/topic")
                .setAutoStartup(true)
                .setRelayHost(environment.getProperty("spring.rabbitmq.host"))
                .setRelayPort(Integer.parseInt(environment.getProperty("app.rabbitmq.stomp.broker.port")))
                .setSystemLogin(environment.getProperty("spring.rabbitmq.username"))
                .setSystemPasscode(environment.getProperty("spring.rabbitmq.password"))
                .setClientLogin(environment.getProperty("spring.rabbitmq.username"))
                .setClientPasscode(environment.getProperty("spring.rabbitmq.password"));*//**//*
        registry.enableSimpleBroker("/queue", "/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }*//*

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app");
        //Working With In-Memory Broker:
        config.enableStompBrokerRelay("/consume", "/queue", "/topic")
                .setRelayHost("localhost")
                .setRelayPort(61613)
                .setSystemLogin(environment.getProperty("spring.rabbitmq.username"))
                .setSystemPasscode(environment.getProperty("spring.rabbitmq.password"))
                .setClientLogin(environment.getProperty("spring.rabbitmq.username"))
                .setClientPasscode(environment.getProperty("spring.rabbitmq.password"));
        //
    }*/
}
