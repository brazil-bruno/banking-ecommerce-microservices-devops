package com.bruno.microservice.order.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {

    private String addressID;

    private String publicArea;

    private String addressNumber;

    private String complement;

    private String neighborhood;

    private String zipCode;

    private String city;

    private String state;
}
