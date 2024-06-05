package com.bruno.microservices.client.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_client")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Client {

    @Id
    private String clientID;

    private String clientName;

    private String clientEmail;

    private String clientPhone;

    private String addressID;
}
