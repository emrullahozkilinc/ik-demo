package com.emr.ikdemobackend.exception;

public class ShiftNotFoundException extends RuntimeException{
    public ShiftNotFoundException(String message) {
        super(message);
    }
}
