package com.emr.ikdemobackend.exception.exceptions;

public class DayoffNotFoundException extends NotFoundException{

    public DayoffNotFoundException(String message) {
        super(message);
    }

    public DayoffNotFoundException() {
        super("This Dayoff Not Found in system.");
    }
}
