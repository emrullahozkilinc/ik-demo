package com.emr.ikdemobackend.dto.request;

import com.emr.ikdemobackend.entity.enums.LeaveType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestDayoffDTO {

    private final int employeeNationalId;
    private final LeaveType leaveType;
    private final int daysOfLeave;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDateTime dateOfStart;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDateTime dateOfEnd;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDateTime dateOfReturn;
    private final String description;
}
