package com.bruno.microservice.order.feignclients;

import com.bruno.microservice.order.entities.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(value = "address", url = "${address.url}")
public interface AddressFeignClient {

    @GetMapping("/api/addresses/{addressID}")
    Address findAddressById(@PathVariable UUID addressID);
}
