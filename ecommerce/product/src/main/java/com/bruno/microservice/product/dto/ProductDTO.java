package com.bruno.microservice.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {

    private String productName;

    private String productDescription;

    private Double productPrice;

    private Integer productQuantity;
}
