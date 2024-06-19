package com.bruno.microservices.account.dto;

import com.bruno.microservices.account.entities.Transaction;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID transactionID;

    @Positive(message = "Must be positive")
    @NotNull(message = "Required field!")
    private double amount;

    private double balance;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    public TransactionDTO(Transaction transaction) {
        this.transactionID = transaction.getTransactionID();
        this.amount = transaction.getAmount();
        this.balance = transaction.getBalance();
        this.createdAt = transaction.getCreatedAt();
    }
}
