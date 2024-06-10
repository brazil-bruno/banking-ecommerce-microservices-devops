package com.bruno.microservice.order.feignclients;

import com.bruno.microservice.order.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@FeignClient(value = "client", url = "${client.url}")
public interface ClientFeignClient {

    @GetMapping("/api/clients/{clientID}")
    @ResponseStatus(HttpStatus.OK)
    Client findClientById(@PathVariable UUID clientID);
}
