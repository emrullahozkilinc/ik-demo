package com.emr.ikdemobackend.security.exception.handler;

import com.emr.ikdemobackend.security.exception.AddRoleException;
import com.emr.ikdemobackend.security.exception.RoleCouldNotFoundException;
import com.emr.ikdemobackend.security.exception.UsernameCouldNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class SecurityExceptionHandler {

    @ExceptionHandler(UsernameCouldNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(){
        return new ResponseEntity<>("Username not found in system.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleCouldNotFoundException.class)
    public ResponseEntity<String> handleRoleNotFoundException(){
        return new ResponseEntity<>("This role couldn't found in system.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddRoleException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(AddRoleException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
