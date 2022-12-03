package com.emr.ikdemobackend.security.controller;

import com.emr.ikdemobackend.security.dto.request.AddRoleToUserDTO;
import com.emr.ikdemobackend.security.dto.request.UserLoginRequestDTO;
import com.emr.ikdemobackend.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/userroles")
@RequiredArgsConstructor
public class UserRoleAPI {

    private final UserService service;

    @PostMapping
    ResponseEntity<String> addRoleToUser(@RequestBody AddRoleToUserDTO dto){
        return ResponseEntity.ok(service.addRoleToUser(dto.getUsername(), dto.getRole()));
    }
}
