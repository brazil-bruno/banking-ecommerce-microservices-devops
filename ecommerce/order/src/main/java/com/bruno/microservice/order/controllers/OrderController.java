package com.bruno.microservice.order.controllers;

import com.bruno.microservice.order.entities.Order;
import com.bruno.microservice.order.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PostMapping("/client-id/{clientID}")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createNewOrder(@RequestBody Order order, @PathVariable UUID clientID) {
        return orderService.createNewOrder(order, clientID);
    }

    @GetMapping("/{orderID}")
    @ResponseStatus(HttpStatus.OK)
    public Order findOrderById(@PathVariable UUID orderID) {
        return orderService.findOrderById(orderID);
    }

}
