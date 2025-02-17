package com.fahim.orderws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OrderWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderWsApplication.class, args);
    }

}
