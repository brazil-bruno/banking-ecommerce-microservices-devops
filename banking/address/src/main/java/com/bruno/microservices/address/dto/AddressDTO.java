package com.bruno.microservices.address.dto;

import com.bruno.microservices.address.entities.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID addressID;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    public AddressDTO(Address address) {
        this.addressID = address.getAddressID();
        this.publicArea = address.getPublicArea();
        this.addressNumber = address.getAddressNumber();
        this.complement = address.getComplement();
        this.neighborhood = address.getNeighborhood();
        this.zipCode = address.getZipCode();
        this.city = address.getCity();
        this.state = address.getState();
        this.createdAt = address.getCreatedAt();
        this.updatedAt = address.getUpdatedAt();
    }
}
