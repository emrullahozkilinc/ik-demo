package com.emr.ikdemobackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ShiftDTO {
    private final int employeeNationalId;
    private final LocalDateTime date;
    private final int hours;
    private final String description;
}
