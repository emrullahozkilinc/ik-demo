package com.emr.ikdemobackend.exception.exceptions;

public class ShiftNotFoundException extends NotFoundException{
    public ShiftNotFoundException(String message) {
        super(message);
    }

    public ShiftNotFoundException() {
        super("This Dayoff not found in system!");
    }
}
