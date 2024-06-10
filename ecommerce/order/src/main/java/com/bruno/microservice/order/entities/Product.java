package com.bruno.microservice.order.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    private UUID productID;

    private String productName;

    private String productDescription;

    private Double productPrice;

    private Integer productQuantity;
}
