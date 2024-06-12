package com.bruno.microservices.client.dto;

import com.bruno.microservices.client.entities.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID clientID;

    private String clientName;

    @NotEmpty(message = "Required field!")
    @Email(message = "Invalid email!")
    private String clientEmail;

    @NotEmpty(message = "Required field!")
    private String clientPhone;

    private UUID addressID;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    public ClientNewDTO(Client client) {
        this.clientID = client.getClientID();
        this.clientName = client.getClientName();
        this.clientEmail = client.getClientEmail();
        this.clientPhone = client.getClientPhone();
        this.addressID = client.getAddressID();
        this.createdAt = client.getCreatedAt();
        this.updatedAt = client.getUpdatedAt();
    }
}
