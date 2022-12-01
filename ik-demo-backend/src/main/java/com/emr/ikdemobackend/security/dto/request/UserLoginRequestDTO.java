package com.emr.ikdemobackend.security.dto.request;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class UserLoginRequestDTO {
    public final String username;
    public final String password;
}
