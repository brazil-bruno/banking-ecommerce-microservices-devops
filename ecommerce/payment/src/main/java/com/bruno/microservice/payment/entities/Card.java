package com.bruno.microservice.payment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Card {

    private UUID cardID;

    private String cardNumber;

    private String cvc;

    private String cardPassword;

    private Double cardLimit;

    private UUID accountID;
}
