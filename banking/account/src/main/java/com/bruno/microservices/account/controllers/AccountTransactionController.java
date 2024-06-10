package com.bruno.microservices.account.controllers;

import com.bruno.microservices.account.entities.Account;
import com.bruno.microservices.account.services.AccountTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class AccountTransactionController {
    private final AccountTransactionService accountTransactionService;

    @PutMapping("/deposit/{accountID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deposit(@RequestBody Account account, @PathVariable UUID accountID) {
        accountTransactionService.deposit(account, accountID);
    }

    @PutMapping("/withdraw/{accountID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw(@RequestBody Account account, @PathVariable UUID accountID) {
        accountTransactionService.withdraw(account, accountID);
    }

    @GetMapping("/balance/{accountID}")
    @ResponseStatus(HttpStatus.OK)
    public double balance(@PathVariable UUID accountID) {
        return accountTransactionService.balance(accountID);
    }
}
