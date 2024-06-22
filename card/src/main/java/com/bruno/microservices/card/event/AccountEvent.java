package com.bruno.microservices.card.event;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@Data
public class AccountEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID accountID;

    private String clientName;
}
