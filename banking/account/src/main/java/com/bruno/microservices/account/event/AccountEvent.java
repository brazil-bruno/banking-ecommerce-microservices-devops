package com.bruno.microservices.account.event;

import com.bruno.microservices.account.entities.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@Data
public class AccountEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID accountID;

    private String clientName;

    public AccountEvent(Account entity) {
        this.accountID = entity.getAccountID();
        this.clientName = entity.getClientName();
    }
}
