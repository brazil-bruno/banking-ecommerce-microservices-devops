package com.bruno.microservices.client.feignclients;

import com.bruno.microservices.client.entities.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(value = "address", url = "${address.url}")
public interface AddressFeignClient {

    @PostMapping("/api/addresses")
    @ResponseStatus(HttpStatus.CREATED)
    Address createNewAddress(@RequestBody Address address);

    @DeleteMapping("/api/addresses/{addressID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAddressById(@PathVariable UUID addressID);
}
