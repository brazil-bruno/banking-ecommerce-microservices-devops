package com.bruno.microservices.card.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {

    private UUID accountID;

    private String accountNumber;

    private String accountPassword;

    private double balance;

    private UUID clientID;
}
