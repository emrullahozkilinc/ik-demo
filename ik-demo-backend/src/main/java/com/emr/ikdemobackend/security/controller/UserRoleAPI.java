package com.emr.ikdemobackend.security.controller;

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
    ResponseEntity<String> addRoleToUser(@RequestBody UserLoginRequestDTO loginDTO){
        return ResponseEntity.ok(service.addRoleToUser(loginDTO.getUsername(), loginDTO.getPassword()));
    }
}
