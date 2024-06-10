package com.bruno.microservice.payment.feignclients;

import com.bruno.microservice.payment.entities.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@FeignClient(value = "order", url = "${order.url}")
public interface OrderFeignClient {

    @GetMapping("/api/orders/{orderID}")
    @ResponseStatus(HttpStatus.OK)
    Order findOrderById(@PathVariable UUID orderID);
}
