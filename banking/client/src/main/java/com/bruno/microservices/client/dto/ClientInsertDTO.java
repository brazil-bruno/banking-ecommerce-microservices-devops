package com.bruno.microservices.client.dto;

import com.bruno.microservices.client.services.validation.ClientInsertValid;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@ClientInsertValid
public class ClientInsertDTO extends ClientDTO {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Required field!")
    @Length( min = 6, max = 6, message = "Must be 6 characters!")
    private String password;

    public ClientInsertDTO() {
        super();
    }
}
