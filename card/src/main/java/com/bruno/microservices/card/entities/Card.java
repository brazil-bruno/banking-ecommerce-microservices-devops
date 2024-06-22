package com.bruno.microservices.card.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_card")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Card implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    private UUID cardID;

    private String cardNumber;

    private String cvc;

    private String cardPassword;

    private double cardLimit;

    @Type(type="uuid-char")
    private UUID accountID;

    private String clientName;
}
