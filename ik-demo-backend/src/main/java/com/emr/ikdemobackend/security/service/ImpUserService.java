package com.emr.ikdemobackend.security.service;

import com.emr.ikdemobackend.security.domain.Role;
import com.emr.ikdemobackend.security.domain.User;
import com.emr.ikdemobackend.security.exception.RoleCouldNotFoundException;
import com.emr.ikdemobackend.security.exception.UsernameCouldNotFoundException;
import com.emr.ikdemobackend.security.repository.RoleRepository;
import com.emr.ikdemobackend.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImpUserService implements UserService, UserDetailsService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    @Override
    public String saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return "User saved.";
    }

    @Override
    public String saveRole(Role role) {
        roleRepo.save(role);
        return "Role saved.";
    }

    @Override
    public String addRoleToUser(String username, String roleName) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(UsernameCouldNotFoundException::new);

        Role role = roleRepo.findByName(roleName)
                .orElseThrow(RoleCouldNotFoundException::new);

        user.getRoles().add(role);
        userRepo.save(user);
        return "Role added to user.";
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(UsernameCouldNotFoundException::new);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(UsernameCouldNotFoundException::new);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
