package com.bruno.microservices.account.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class AccountNewDTO extends AccountDTO {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Required field!")
    @Length( min = 6, max = 6, message = "Must be 6 characters!")
    private String accountPassword;

    public AccountNewDTO() {
        super();
    }
}
