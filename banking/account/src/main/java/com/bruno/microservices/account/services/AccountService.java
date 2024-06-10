package com.bruno.microservices.account.services;

import com.bruno.microservices.account.dto.AccountDTO;
import com.bruno.microservices.account.entities.Account;
import com.bruno.microservices.account.entities.Client;
import com.bruno.microservices.account.feignclients.ClientFeignClient;
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

    private final ClientFeignClient clientFeignClient;

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Account createNewAccount(AccountDTO accountDTO, UUID clientID) {
        Client client = clientFeignClient.findClientById(clientID);

        Account entity = Account.builder()
                .accountNumber(accountNumberGenerate())
                .accountBalance(0.0)
                .accountPassword(accountDTO.getAccountPassword())
                .clientID(client.getClientID())
                .build();
        return accountRepository.save(entity);
    }

    public Account findAccountById(UUID accountID) {
        return accountRepository.findById(accountID).get();
    }

    public Account updateAccount(Account account, UUID accountID) {
        Account entity = accountRepository.findById(accountID).get();
        entity.setAccountPassword(account.getAccountPassword());
        return accountRepository.save(entity);
    }

    public void deleteAccountById(UUID accountID) {
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
