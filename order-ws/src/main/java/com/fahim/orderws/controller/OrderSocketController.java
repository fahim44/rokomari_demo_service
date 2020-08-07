package com.fahim.orderws.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class OrderSocketController {

    //FIXME
    @MessageMapping("/newOrder")
    @SendTo("/topic/newOrder")
    public String newOrder(@Payload String order) {
        log.info(order);
        return order;
    }
}
