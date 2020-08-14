package com.fahim.service;

import com.fahim.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("order-ws")
public interface OrderService {

    @PostMapping("/order/newOrder")
    ResponseEntity<Order> createNewOrder(@RequestBody Order order);
}