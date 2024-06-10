package com.bruno.microservices.address.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_address")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID addressID;

    private String publicArea;

    private String addressNumber;

    private String complement;

    private String neighborhood;

    private String zipCode;

    private String city;

    private String state;
}
