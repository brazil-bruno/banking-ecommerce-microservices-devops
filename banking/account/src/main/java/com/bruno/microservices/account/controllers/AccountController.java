package com.bruno.microservices.account.controllers;

import com.bruno.microservices.account.dto.AccountDTO;
import com.bruno.microservices.account.dto.AccountNewDTO;
import com.bruno.microservices.account.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping(value = "find-all")
    @ResponseStatus(HttpStatus.OK)
    public Page<AccountDTO> findAllAccounts(Pageable pageable) {
        return accountService.findAllAccounts(pageable);
    }

    @PostMapping(value = "/create-by-client-id/{clientID}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createNewAccount(@RequestBody @Valid AccountNewDTO accountNewDTO, @PathVariable UUID clientID) {
        return accountService.createNewAccount(accountNewDTO, clientID);
    }

    @GetMapping(value = "/find-by-id/{accountID}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO findAccountById(@PathVariable UUID accountID) {
        return accountService.findAccountById(accountID);
    }

    @PutMapping(value = "/update/{accountID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AccountDTO updateAccount(@RequestBody @Valid AccountNewDTO accountNewDTO, @PathVariable UUID accountID) {
        return accountService.updateAccount(accountNewDTO, accountID);
    }

    @DeleteMapping(value = "/delete/{accountID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountById(@PathVariable UUID accountID) {
        accountService.deleteAccountById(accountID);
    }
}
