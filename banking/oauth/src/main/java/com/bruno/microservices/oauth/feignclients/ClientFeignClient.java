package com.bruno.microservices.oauth.feignclients;

import com.bruno.microservices.oauth.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@FeignClient(value = "client", url = "${client.url}")
public interface ClientFeignClient {

    @GetMapping(value = "/api/clients/find-by-email")
    @ResponseStatus(HttpStatus.OK)
    Client findClientByEmail(@RequestParam String email);

}
