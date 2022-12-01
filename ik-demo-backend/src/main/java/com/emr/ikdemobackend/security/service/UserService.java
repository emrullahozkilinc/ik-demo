package com.emr.ikdemobackend.security.service;

import com.emr.ikdemobackend.security.domain.Role;
import com.emr.ikdemobackend.security.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    String saveUser(User user);
    String saveRole(Role role);
    String addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getAllUsers();
}
