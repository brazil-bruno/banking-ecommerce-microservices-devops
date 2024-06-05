package com.bruno.microservices.card.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_card")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Card {

    @Id
    private String cardID;

    private String cardNumber;

    private String cvc;

    private String cardPassword;

    private Double cardLimit;

    private String accountID;
}
