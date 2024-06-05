package com.bruno.microservices.account.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_account")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Account {

    @Id
    private String accountID;

    private String accountNumber;

    private double accountBalance;

    private String accountPassword;

    private String clientID;

}
