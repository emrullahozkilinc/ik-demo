package com.emr.ikdemobackend.exception;

public class DayoffNotFoundException extends RuntimeException{

    public DayoffNotFoundException(String message) {
        super(message);
    }
}
