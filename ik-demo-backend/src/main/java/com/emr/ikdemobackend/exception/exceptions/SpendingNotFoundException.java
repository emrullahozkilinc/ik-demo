package com.emr.ikdemobackend.exception.exceptions;

public class SpendingNotFoundException extends NotFoundException{
    public SpendingNotFoundException(String message) {
        super(message);
    }

    public SpendingNotFoundException() {
        super("This spending not found in system!");
    }
}
