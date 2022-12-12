package com.emr.ikdemobackend.security.conf;

import com.emr.ikdemobackend.security.filter.AuthFilter;
import com.emr.ikdemobackend.security.filter.CorsFilter;
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
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConf {
    private final UserDetailsService detailsService;
    private final PasswordEncoder encoder;

    /*
        authorize ile verdiğimiz kurallar dışındaki tüm istekleri token istemeden kabul ediyor
     */
    @Bean
    SecurityFilterChain web(HttpSecurity http, AuthenticationManager manager) throws Exception{
        http.addFilter(new AuthFilter(manager));
        http.addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new CorsFilter(), SessionManagementFilter.class);
        http.csrf().disable();
        http.cors().disable();

        http.authorizeHttpRequests().requestMatchers("/login").permitAll();
        http.authorizeHttpRequests().requestMatchers("/api/v1/auth/**").hasAuthority("ROLE_ADMIN");
        http.authorizeHttpRequests().requestMatchers("/api/v1/app/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_HR");
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

    @Bean
    CorsConfigurationSource configureCors(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/v1/app/**", configuration);
        return source;
    }
}
