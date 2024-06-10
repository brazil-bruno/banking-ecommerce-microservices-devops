package com.bruno.microservice.order.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_order_item")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderItemID;

    private Integer quantity;

    private UUID productID;

    private String productName;

    private double productPrice;

    private double subTotal;

    public double getSubTotal() {
        return subTotal = productPrice * quantity;
    }
}
