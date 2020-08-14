package com.fahim.orderws.controller;

import com.fahim.orderws.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/newOrder")
    public ResponseEntity<Order> createNewOrder(@RequestBody Order order) {
        order.setId(UUID.randomUUID().toString());
        log.info(order.toString());
        simpMessagingTemplate.convertAndSend("/topic/newOrder", order);
        return ResponseEntity.ok(order);
    }
}