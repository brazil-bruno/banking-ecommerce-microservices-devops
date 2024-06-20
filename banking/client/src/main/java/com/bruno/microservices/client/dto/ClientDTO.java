package com.bruno.microservices.client.dto;

import com.bruno.microservices.client.entities.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID clientID;

    @NotEmpty(message = "Required field!")
    @Length(min = 3, max = 20, message = "From 3 to 120 characters!")
    private String clientName;

    @NotEmpty(message = "Required field!")
    @Email(message = "Invalid email!")
    private String email;

    @NotEmpty(message = "Required field!")
    private String clientPhone;

    private Set<RoleDTO> roles = new HashSet<>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    public ClientDTO(Client client) {
        this.clientID = client.getClientID();
        this.clientName = client.getClientName();
        this.email = client.getEmail();
        this.clientPhone = client.getClientPhone();
        this.createdAt = client.getCreatedAt();
        this.updatedAt = client.getUpdatedAt();
        client.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }
}
