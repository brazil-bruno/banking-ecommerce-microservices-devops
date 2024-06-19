package com.bruno.microservices.account.feignclients;

import com.bruno.microservices.account.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@FeignClient(value = "client", url = "${client.url}")
public interface ClientFeignClient {
    @GetMapping("/api/clients/find-by-id/{clientID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Client findClientById(@PathVariable UUID clientID);
}
