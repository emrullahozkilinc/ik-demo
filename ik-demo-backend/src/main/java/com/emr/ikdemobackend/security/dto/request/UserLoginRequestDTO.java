package com.emr.ikdemobackend.security.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestDTO {
    public String username;
    public String password;
}
