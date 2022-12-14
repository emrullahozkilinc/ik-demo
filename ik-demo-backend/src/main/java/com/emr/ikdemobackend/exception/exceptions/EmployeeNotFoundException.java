package com.emr.ikdemobackend.exception.exceptions;

public class EmployeeNotFoundException extends NotFoundException{
    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException() {
        super("This Employee not found in system!");
    }
}
