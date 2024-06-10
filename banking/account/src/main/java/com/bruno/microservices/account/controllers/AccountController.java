package com.bruno.microservices.account.controllers;

import com.bruno.microservices.account.dto.AccountDTO;
import com.bruno.microservices.account.entities.Account;
import com.bruno.microservices.account.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAllAccounts() {
        return accountService.findAllAccounts();
    }

    @PostMapping("client-id/{clientID}")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createNewAccount(@RequestBody AccountDTO accountDTO, @PathVariable UUID clientID) {
        return accountService.createNewAccount(accountDTO, clientID);
    }

    @GetMapping("/{accountID}")
    @ResponseStatus(HttpStatus.OK)
    public Account findAccountById(@PathVariable UUID accountID) {
        return accountService.findAccountById(accountID);
    }

    @PutMapping("/{accountID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Account updateAccount(@RequestBody Account account, @PathVariable UUID accountID) {
        return accountService.updateAccount(account, accountID);
    }

    @DeleteMapping("/{accountID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountById(@PathVariable UUID accountID) {
        accountService.deleteAccountById(accountID);
    }
}
