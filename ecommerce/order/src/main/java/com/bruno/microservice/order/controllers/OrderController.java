package com.bruno.microservice.order.controllers;

import com.bruno.microservice.order.entities.Order;
import com.bruno.microservice.order.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> findAllOrders() {
        return orderService.findAllOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createNewOrder(@RequestBody Order order) {
        return orderService.createNewOrder(order);
    }

}
