package com.bruno.microservice.order.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {

    private String clientID;

    private String clientName;

    private String clientEmail;

    private String clientPhone;

    private String clientPassword;

    private String addressID;
}
