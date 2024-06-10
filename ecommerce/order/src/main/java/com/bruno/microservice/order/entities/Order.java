package com.bruno.microservice.order.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_order")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderID;

    private String clientName;

    private String clientPhone;

    private String publicArea;

    private String addressNumber;

    private String complement;

    private String neighborhood;

    private String zipCode;

    private String city;

    private String state;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;

    private double totalAmount;

    public double getTotalAmount() {
        for (OrderItem orderItem : orderItems) {
            totalAmount += orderItem.getSubTotal();
        }
        return totalAmount;
    }
}
