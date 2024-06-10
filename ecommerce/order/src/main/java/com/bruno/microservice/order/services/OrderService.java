package com.bruno.microservice.order.services;

import com.bruno.microservice.order.entities.*;
import com.bruno.microservice.order.feignclients.ProductFeignClient;
import com.bruno.microservice.order.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductFeignClient productFeignClient;

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order createNewOrder(Order order) {

        for (OrderItem orderItem: order.getOrderItems()) {
            Product entity = productFeignClient.findProductById(orderItem.getProductID());

            orderItem.setProductName(entity.getProductName());
            orderItem.setProductPrice(entity.getProductPrice());
        }

        return orderRepository.save(order);
    }
}
