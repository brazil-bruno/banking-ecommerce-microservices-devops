package com.bruno.microservice.payment.feignclients;

import com.bruno.microservice.payment.entities.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(value = "card", url = "${card.url}")
public interface CardFeignClient {

    @GetMapping("/api/cards/{cardID}")
    @ResponseStatus(HttpStatus.OK)
    Card findCardById(@PathVariable UUID cardID);

    @PutMapping("/api/cards/{cardID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Card updateCard(@RequestBody Card card, @PathVariable UUID cardID);
}
