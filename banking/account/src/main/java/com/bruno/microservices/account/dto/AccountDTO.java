package com.bruno.microservices.account.dto;

import com.bruno.microservices.account.entities.Account;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Required field!")
    @Length(min = 6, max = 6, message = "Must be 6 characters!")
    private String accountPassword;

    private UUID clientID;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    public AccountDTO(Account account) {
        this.accountID = account.getAccountID();
        this.accountNumber = account.getAccountNumber();
        this.accountBalance = account.getAccountBalance();
        this.accountPassword = account.getAccountPassword();
        this.clientID = account.getClientID();
        this.createdAt = account.getCreatedAt();
        this.updatedAt = account.getUpdatedAt();
    }
}
