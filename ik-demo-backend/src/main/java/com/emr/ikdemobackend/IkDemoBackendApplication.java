package com.emr.ikdemobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class IkDemoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(IkDemoBackendApplication.class, args);
	}

	@Bean
	public PasswordEncoder setEncoder(){
		return new BCryptPasswordEncoder();
	}
}
