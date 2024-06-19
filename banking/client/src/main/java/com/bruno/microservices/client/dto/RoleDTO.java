package com.bruno.microservices.client.dto;

import com.bruno.microservices.client.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Required field!")
    private UUID roleID;

    @NotEmpty(message = "Required field!")
    private String authority;

    public RoleDTO(Role role) {
        this.roleID = role.getRoleID();
        this.authority = role.getAuthority();
    }
}
