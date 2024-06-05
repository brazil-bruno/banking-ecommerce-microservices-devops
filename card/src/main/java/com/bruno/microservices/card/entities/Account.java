package com.bruno.microservices.card.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {

    private String accountID;

    private String accountNumber;

    private String accountPassword;

    private double balance;

    private String clientID;
}
