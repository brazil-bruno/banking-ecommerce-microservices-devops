package com.bruno.microservices.oauth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID roleID;

    private String authority;
}
