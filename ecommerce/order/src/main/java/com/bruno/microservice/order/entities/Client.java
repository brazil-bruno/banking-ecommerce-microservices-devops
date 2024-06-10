package com.bruno.microservice.order.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {

    private UUID clientID;

    private String clientName;

    private String clientEmail;

    private String clientPhone;

    private String clientPassword;

    private UUID addressID;
}
