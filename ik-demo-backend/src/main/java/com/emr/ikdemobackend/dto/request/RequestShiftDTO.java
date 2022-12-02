package com.emr.ikdemobackend.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestShiftDTO {
    private final int employeeNationalId;
    private final LocalDateTime date;
    private final int hours;
    private final String description;
}
