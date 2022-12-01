package com.emr.ikdemobackend.security.conf;

import com.emr.ikdemobackend.security.filter.AuthFilter;
import com.emr.ikdemobackend.security.filter.JWTFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConf {
    private final UserDetailsService detailsService;
    private final PasswordEncoder encoder;

    @Bean
    SecurityFilterChain web(HttpSecurity http, AuthenticationManager manager) throws Exception{
        http.addFilter(new AuthFilter(manager));
        http.addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
        http.authorizeHttpRequests().anyRequest().authenticated();
        return http.build();
    }

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(detailsService)
                .passwordEncoder(encoder)
                .and().build();
    }
}
