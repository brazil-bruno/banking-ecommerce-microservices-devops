package com.bruno.microservices.client.feignclients;

import com.bruno.microservices.client.entities.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(value = "address", url = "${address.url}")
public interface AddressFeignClient {

    @GetMapping("/api/addresses/find-address-by-client-id/{clientID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Address findAddressByClientId(@PathVariable UUID clientID);

    @DeleteMapping("/api/addresses/delete-address-by-client-id/{clientID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAddressByClientId(@PathVariable UUID clientID);
}
