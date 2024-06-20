package com.bruno.microservices.account.services;

import com.bruno.microservices.account.dto.AccountDTO;
import com.bruno.microservices.account.dto.AccountNewDTO;
import com.bruno.microservices.account.entities.Account;
import com.bruno.microservices.account.entities.Client;
import com.bruno.microservices.account.feignclients.ClientFeignClient;
import com.bruno.microservices.account.repositories.AccountRepository;
import com.bruno.microservices.account.services.exceptions.DataBaseException;
import com.bruno.microservices.account.services.exceptions.ResourceNotFoundException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AccountRepository accountRepository;

    private final ClientFeignClient clientFeignClient;

    public Page<AccountDTO> findAllAccounts(Pageable pageable) {
        Page<Account> accounts = accountRepository.findAll(pageable);
        return accounts.map(account -> new AccountDTO(account));
    }

    public AccountDTO createNewAccount(AccountNewDTO accountNewDTO, UUID clientID) {
        try {
            Client client = clientFeignClient.findClientById(clientID);
            Account entity = Account.builder()
                    .accountNumber(accountNumberGenerate())
                    .accountBalance(0.0)
                    .accountPassword(bCryptPasswordEncoder.encode(accountNewDTO.getAccountPassword()))
                    .clientID(client.getClientID())
                    .clientName(client.getClientName())
                    .createdAt(new Date())
                    .build();
            accountRepository.save(entity);
            return new AccountDTO(entity);
        } catch (FeignException e) {
            throw new ResourceNotFoundException("Client not found");
        }
    }

    public AccountDTO findAccountById(UUID accountID) {
        Optional<Account> account = accountRepository.findById(accountID);
        Account entity = account.orElseThrow(() -> new ResourceNotFoundException("Account not found!"));
        return new AccountDTO(entity);
    }

    @Transactional
    public AccountDTO updateAccount(AccountNewDTO accountNewDTO, UUID accountID) {
        try {
            Account account = accountRepository.getOne(accountID);
            account.setAccountPassword(bCryptPasswordEncoder.encode(accountNewDTO.getAccountPassword()));
            account.setUpdatedAt(new Date());
            account = accountRepository.save(account);
            return new AccountDTO(account);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Account not found");
        }
    }

    public void deleteAccountById(UUID accountID) {
        try {
            accountRepository.deleteById(accountID);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Account not found");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation!.");
        }
    }

    private String accountNumberGenerate() {
        Random rand = new Random();
        String accountNumber = "0462";
        for (int i = 0; i < 8; i++) {
            int n = rand.nextInt(10) + 0;
            accountNumber += Integer.toString(n);
        }
        return accountNumber;
    }
}
