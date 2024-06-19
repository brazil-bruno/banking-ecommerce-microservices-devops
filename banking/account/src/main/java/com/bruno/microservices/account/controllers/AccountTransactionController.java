package com.bruno.microservices.account.controllers;

import com.bruno.microservices.account.dto.TransactionDTO;
import com.bruno.microservices.account.entities.Account;
import com.bruno.microservices.account.services.AccountTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class AccountTransactionController {
    private final AccountTransactionService accountTransactionService;

    @PostMapping("/deposit/{accountID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deposit(@RequestBody @Valid TransactionDTO transactionDTO, @PathVariable UUID accountID) {
        accountTransactionService.deposit(transactionDTO, accountID);
    }

    @PostMapping("/withdraw/{accountID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw(@RequestBody @Valid TransactionDTO transactionDTO, @PathVariable UUID accountID) {
        accountTransactionService.withdraw(transactionDTO, accountID);
    }

    @GetMapping("/balance/{accountID}")
    @ResponseStatus(HttpStatus.OK)
    public double balance(@PathVariable UUID accountID) {
        return accountTransactionService.balance(accountID);
    }

    @GetMapping(value = "/find-transactions-by-account-id/{accountID}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDTO> findAllTransactionsByAccountId(@PathVariable UUID accountID) {
        return accountTransactionService.findAllTransactionsByAccountId(accountID);
    }
}
