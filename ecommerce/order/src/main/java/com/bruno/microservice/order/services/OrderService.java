package com.bruno.microservice.order.services;

import com.bruno.microservice.order.entities.*;
import com.bruno.microservice.order.feignclients.AddressFeignClient;
import com.bruno.microservice.order.feignclients.ClientFeignClient;
import com.bruno.microservice.order.feignclients.ProductFeignClient;
import com.bruno.microservice.order.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductFeignClient productFeignClient;

    private final ClientFeignClient clientFeignClient;

    private final AddressFeignClient addressFeignClient;

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order createNewOrder(Order order, UUID clientID) {
        Client client = clientFeignClient.findClientById(clientID);
        Address address = addressFeignClient.findAddressById(client.getAddressID());

        Order orderEntity = Order.builder()
                .clientName(client.getClientName())
                .clientPhone(client.getClientPhone())
                .publicArea(address.getPublicArea())
                .addressNumber(address.getAddressNumber())
                .complement(address.getComplement())
                .neighborhood(address.getNeighborhood())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .state(address.getState())
                .orderItems(order.getOrderItems())
                .build();


        for (OrderItem orderItem: order.getOrderItems()) {
            Product entity = productFeignClient.findProductById(orderItem.getProductID());

            orderItem.setProductName(entity.getProductName());
            orderItem.setProductPrice(entity.getProductPrice());
        }

        return orderRepository.save(orderEntity);
    }

    public Order findOrderById(UUID orderID) {
        return orderRepository.findById(orderID).get();
    }
}
