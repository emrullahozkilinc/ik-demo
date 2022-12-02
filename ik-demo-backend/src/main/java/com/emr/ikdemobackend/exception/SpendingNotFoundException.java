package com.emr.ikdemobackend.exception;

public class SpendingNotFoundException extends RuntimeException{
    public SpendingNotFoundException(String message) {
        super(message);
    }
}
