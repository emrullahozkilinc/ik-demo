package com.emr.ikdemobackend.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestShiftDTO {
    private final Long employeeNationalId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDateTime date;
    private final int hours;
    private final String description;
}
