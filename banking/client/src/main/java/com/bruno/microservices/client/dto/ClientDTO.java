package com.bruno.microservices.client.dto;

import com.bruno.microservices.client.entities.Address;
import com.bruno.microservices.client.entities.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID clientID;

    @NotEmpty(message = "Required field!")
    @Length(min = 5, max = 120, message = "from 5 to 120 characters!")
    private String clientName;

    @NotEmpty(message = "Required field!")
    @Email(message = "Invalid email!")
    private String clientEmail;

    @NotEmpty(message = "Required field!")
    private String clientPhone;

    @NotEmpty(message = "Required field!")
    private String publicArea;

    @NotEmpty(message = "Required field!")
    private String addressNumber;

    private String complement;

    @NotEmpty(message = "Required field!")
    private String neighborhood;

    @NotEmpty(message = "Required field!")
    private String zipCode;

    @NotEmpty(message = "Required field!")
    private String city;

    @NotEmpty(message = "Required field!")
    private String state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    public ClientDTO(Client client, Address address) {
        this.clientID = client.getClientID();
        this.clientName = client.getClientName();
        this.clientEmail = client.getClientEmail();
        this.clientPhone = client.getClientPhone();
        this.publicArea = address.getPublicArea();
        this.addressNumber = address.getAddressNumber();
        this.complement = address.getComplement();
        this.neighborhood = address.getNeighborhood();
        this.zipCode = address.getZipCode();
        this.city = address.getCity();
        this.state = address.getState();
        this.createdAt = client.getCreatedAt();
    }
}
