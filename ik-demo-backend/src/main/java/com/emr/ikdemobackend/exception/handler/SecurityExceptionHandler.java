package com.emr.ikdemobackend.exception.handler;

import com.emr.ikdemobackend.security.exception.SecurityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler({SecurityNotFoundException.class})
    public ResponseEntity<String> handleSecurityNotFoundExceptions(SecurityNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
