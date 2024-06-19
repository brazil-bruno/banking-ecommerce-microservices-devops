package com.bruno.microservices.account.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID clientID;

    private String clientName;
}
