package com.emr.ikdemobackend.exception.exceptions;

public class DateConflictException extends RuntimeException{

    public DateConflictException() {
        super("Some Dates conflicts. Please check previous data of given employee!");
    }

    public DateConflictException(String message) {
        super(message);
    }
}
