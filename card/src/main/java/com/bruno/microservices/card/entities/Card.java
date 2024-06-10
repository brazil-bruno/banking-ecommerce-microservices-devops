package com.bruno.microservices.card.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_card")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID cardID;

    private String cardNumber;

    private String cvc;

    private String cardPassword;

    private Double cardLimit;

    private UUID accountID;
}
