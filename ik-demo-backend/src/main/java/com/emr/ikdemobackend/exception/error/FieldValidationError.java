package com.emr.ikdemobackend.exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldValidationError {
    String message;
    String field;
    String value;
}
