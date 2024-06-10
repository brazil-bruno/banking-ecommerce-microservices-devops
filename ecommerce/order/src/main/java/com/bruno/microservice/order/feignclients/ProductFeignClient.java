package com.bruno.microservice.order.feignclients;

import com.bruno.microservice.order.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(value = "product", url = "${product.url}")
public interface ProductFeignClient {

    @GetMapping("/api/products/{productID}")
    Product findProductById(@PathVariable UUID productID);
}
