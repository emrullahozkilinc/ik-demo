package com.emr.ikdemobackend.exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotFoundError {
    String status;
    String message;
}
