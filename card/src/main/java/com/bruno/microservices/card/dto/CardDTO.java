package com.bruno.microservices.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardDTO {

    private String cardNumber;

    private String cvc;

    private String cardPassword;

    private Double cardLimit;

    private String accountID;
}
