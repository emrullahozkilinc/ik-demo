package com.emr.ikdemobackend.security.controller;

import com.emr.ikdemobackend.security.domain.User;
import com.emr.ikdemobackend.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/users")
@RequiredArgsConstructor
public class UserAPI {

    private final UserService service;

    @GetMapping
    ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PostMapping
    ResponseEntity<String> addUser(@RequestBody User user){
        return ResponseEntity.ok(service.saveUser(user));
    }
}

