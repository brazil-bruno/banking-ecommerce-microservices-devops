package com.bruno.microservices.account.feignclients;

import com.bruno.microservices.account.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(value = "client", url = "${client.url}")
public interface ClientFeignClient {

    @GetMapping("/api/clients/{clientID}")
    Client findClientById(@PathVariable UUID clientID);
}
