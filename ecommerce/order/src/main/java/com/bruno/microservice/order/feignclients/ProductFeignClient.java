package com.bruno.microservice.order.feignclients;

import com.bruno.microservice.order.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(value = "product", url = "${product.url}")
public interface ProductFeignClient {

    @GetMapping("/api/products/{productID}")
    @ResponseStatus(HttpStatus.OK)
    Product findProductById(@PathVariable UUID productID);

    @PutMapping("/api/products/{productID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Product updateProduct(@RequestBody Product product, @PathVariable UUID productID);
}
