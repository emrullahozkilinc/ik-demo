package com.emr.ikdemobackend.exception.handler;

import com.emr.ikdemobackend.exception.error.FieldValidationError;
import com.emr.ikdemobackend.exception.exceptions.NotFoundException;
import com.emr.ikdemobackend.mapper.FieldValidationErrorMapper;
import com.emr.ikdemobackend.mapper.MethodArgumentErrorMapper;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MethodArgumentErrorMapper errorMapper;
    private final FieldValidationErrorMapper fieldMapper;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundExceptions(NotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Set<FieldValidationError>> handleFieldExceptions(ConstraintViolationException exception){
        var errors = exception.getConstraintViolations()
                .stream().map(fieldMapper::toFieldValidationErrorFromConstraintViolation)
                .collect(Collectors.toSet());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Set<FieldValidationError>> handleArgumentExceptions(MethodArgumentNotValidException exception){
        var errors = exception.getFieldErrors()
                .stream().map(errorMapper::toFieldValidationErrorFromFieldError)
                .collect(Collectors.toSet());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleUniqueErrors(DataIntegrityViolationException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleEnumErrors() {
        return new ResponseEntity<>("Some inputs wrong!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public void handleGlobalExceptions(Exception exception){
        exception.printStackTrace();
    }
}
