package com.bruno.microservices.card.feignclients;

import com.bruno.microservices.card.entities.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(value = "gateway", url = "${gateway.url}")
public interface AccountFeignClient {
    @GetMapping("/account/api/accounts/{accountID}")
    @ResponseStatus(HttpStatus.OK)
    Account findAccountById(@PathVariable String accountID);
}
