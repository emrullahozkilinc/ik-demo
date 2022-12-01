package com.emr.ikdemobackend.security.controller;

import com.emr.ikdemobackend.security.domain.Role;
import com.emr.ikdemobackend.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/roles")
@RequiredArgsConstructor
public class RoleAPI {

    private final UserService service;

    @PostMapping
    ResponseEntity<String> addRole(@RequestBody Role role){
        return ResponseEntity.ok(service.saveRole(role));
    }
}
