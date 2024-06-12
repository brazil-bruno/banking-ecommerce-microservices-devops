package com.bruno.microservices.account.controllers;

import com.bruno.microservices.account.dto.AccountDTO;
import com.bruno.microservices.account.entities.Account;
import com.bruno.microservices.account.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<AccountDTO> findAllAccounts(Pageable pageable) {
        return accountService.findAllAccounts(pageable);
    }

    @PostMapping("/client-id/{clientID}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createNewAccount(@RequestBody @Valid AccountDTO accountDTO, @PathVariable UUID clientID) {
        return accountService.createNewAccount(accountDTO, clientID);
    }

    @GetMapping("/{accountID}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO findAccountById(@PathVariable UUID accountID) {
        return accountService.findAccountById(accountID);
    }

    @PutMapping("/{accountID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AccountDTO updateAccount(@RequestBody @Valid AccountDTO accountDTO, @PathVariable UUID accountID) {
        return accountService.updateAccount(accountDTO, accountID);
    }

    @DeleteMapping("/{accountID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountById(@PathVariable UUID accountID) {
        accountService.deleteAccountById(accountID);
    }
}
