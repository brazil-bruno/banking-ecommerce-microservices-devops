package com.bruno.microservices.account.controllers;

import com.bruno.microservices.account.entities.Account;
import com.bruno.microservices.account.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createNewAccount(@RequestBody Account account) {
        return accountService.createNewAccount(account);
    }

    @GetMapping("/{accountID}")
    @ResponseStatus(HttpStatus.OK)
    public Account findAccountById(@PathVariable String accountID) {
        return accountService.findAccountById(accountID);
    }

    @PutMapping("/{accountID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Account updateAccount(@RequestBody Account account, @PathVariable String accountID) {
        return accountService.updateAccount(account, accountID);
    }

    @DeleteMapping("/{accountID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountById(@PathVariable String accountID) {
        accountService.deleteAccountById(accountID);
    }
}
