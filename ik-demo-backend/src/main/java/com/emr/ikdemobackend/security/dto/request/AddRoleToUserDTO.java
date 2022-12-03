package com.emr.ikdemobackend.security.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddRoleToUserDTO {
    String username;
    String role;
}
