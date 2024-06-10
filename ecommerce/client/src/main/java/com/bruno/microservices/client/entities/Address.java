package com.bruno.microservices.client.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Address {

    private UUID addressID;

    private String publicArea;

    private String addressNumber;

    private String complement;

    private String neighborhood;

    private String zipCode;

    private String city;

    private String state;
}
