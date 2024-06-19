package com.bruno.microservices.account.services;


import com.bruno.microservices.account.dto.TransactionDTO;
import com.bruno.microservices.account.entities.Account;
import com.bruno.microservices.account.entities.Transaction;
import com.bruno.microservices.account.repositories.AccountRepository;
import com.bruno.microservices.account.repositories.TransactionRepository;
import com.bruno.microservices.account.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    public void deposit(TransactionDTO obj, UUID accountID) {
        Optional<Account> account = accountRepository.findById(accountID);
        Account entity = account.orElseThrow(() -> new ResourceNotFoundException("Account not found!"));
        double balance = entity.getAccountBalance();
        double amount = obj.getAmount();
        entity.setAccountBalance(balance + amount);
        entity = accountRepository.save(entity);

        Transaction transaction = Transaction.builder()
                .amount(obj.getAmount())
                .balance(entity.getAccountBalance())
                .accountID(entity.getAccountID())
                .createdAt(new Date())
                .build();
        transactionRepository.save(transaction);

    }

    public void withdraw(TransactionDTO obj, UUID accountID) {

        Optional<Account> account = accountRepository.findById(accountID);
        Account entity = account.orElseThrow(() -> new ResourceNotFoundException("Account not found!"));
        double balance = entity.getAccountBalance();
        double amount = obj.getAmount();
        entity.setAccountBalance(balance - amount);
        entity = accountRepository.save(entity);

        Transaction transaction = Transaction.builder()
                .amount(obj.getAmount())
                .balance(entity.getAccountBalance())
                .accountID(entity.getAccountID())
                .createdAt(new Date())
                .build();
        transactionRepository.save(transaction);

    }

    public double balance(UUID accountID) {
        Optional<Account> account = accountRepository.findById(accountID);
        Account entity = account.orElseThrow(() -> new ResourceNotFoundException("Account not found!"));
        return entity.getAccountBalance();

    }

    public List<TransactionDTO> findAllTransactionsByAccountId(UUID accountID) {
        Optional<Account> account = accountRepository.findById(accountID);
        Account entity = account.orElseThrow(() -> new ResourceNotFoundException("Account not found!"));
        List<Transaction> transactions = transactionRepository.findAllByAccountID(entity.getAccountID());
        return transactions.stream().map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toList());
    }
}
