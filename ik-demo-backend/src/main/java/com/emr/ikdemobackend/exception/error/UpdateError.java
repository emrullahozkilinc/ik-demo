package com.emr.ikdemobackend.exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UpdateError {
    String status;
    List<String> message;
}
