package com.bruno.microservices.account.services;


import com.bruno.microservices.account.entities.Account;
import com.bruno.microservices.account.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountRepository accountRepository;

    public void deposit(Account account, String accountID) {

        Account entity = accountRepository.findById(accountID).get();
        double balance = entity.getAccountBalance();
        double amount = account.getAccountBalance();
        entity.setAccountBalance(balance + amount);
        accountRepository.save(entity);

    }

    public void withdraw(Account account, String accountID) {

        Account entity = accountRepository.findById(accountID).get();
        double balance = entity.getAccountBalance();
        double amount = account.getAccountBalance();
        entity.setAccountBalance(balance - amount);
        accountRepository.save(entity);

    }

    public double balance(String accountID) {

        Account entity = accountRepository.findById(accountID).get();
        return entity.getAccountBalance();

    }
}
