package com.bruno.microservices.account.dto;

import com.bruno.microservices.account.entities.Account;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID accountID;

    private String accountNumber;

    private double accountBalance;

    private UUID clientID;

    private String clientName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    public AccountDTO(Account account) {
        this.accountID = account.getAccountID();
        this.accountNumber = account.getAccountNumber();
        this.accountBalance = account.getAccountBalance();
        this.clientID = account.getClientID();
        this.clientName = account.getClientName();
        this.createdAt = account.getCreatedAt();
        this.updatedAt = account.getUpdatedAt();
    }
}
