package com.bruno.microservices.account.services;

import com.bruno.microservices.account.entities.Account;
import com.bruno.microservices.account.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Account createNewAccount(Account account) {
        Account entity = Account.builder()
                .accountID(UUID.randomUUID().toString())
                .accountNumber(accountNumberGenerate())
                .accountBalance(0.0)
                .accountPassword(account.getAccountPassword())
                .build();
        return accountRepository.save(entity);
    }

    public Account findAccountById(String accountID) {
        return accountRepository.findById(accountID).get();
    }

    public Account updateAccount(Account account, String accountID) {
        Account entity = accountRepository.findById(accountID).get();
        entity.setAccountPassword(account.getAccountPassword());
        return accountRepository.save(entity);
    }

    public void deleteAccountById(String accountID) {
        accountRepository.deleteById(accountID);
    }

    private String accountNumberGenerate() {
        Random rand = new Random();
        String accountNumber = "0462";
        for (int i = 0; i < 8; i++)
        {
            int n = rand.nextInt(10) + 0;
            accountNumber += Integer.toString(n);
        }
        return accountNumber;
    }
}
