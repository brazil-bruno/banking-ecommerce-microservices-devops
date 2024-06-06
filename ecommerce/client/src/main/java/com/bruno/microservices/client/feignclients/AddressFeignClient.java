package com.bruno.microservices.client.feignclients;

import com.bruno.microservices.client.entities.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "address", url = "${address.url}")
public interface AddressFeignClient {

    @PostMapping("/api/addresses")
    @ResponseStatus(HttpStatus.CREATED)
    Address createNewAddress(@RequestBody Address address);

    @GetMapping("/{addressID}")
    Address findAddressById(@PathVariable String addressID);
}
