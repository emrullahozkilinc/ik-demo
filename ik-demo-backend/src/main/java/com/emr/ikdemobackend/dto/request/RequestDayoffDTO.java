package com.emr.ikdemobackend.dto.request;

import com.emr.ikdemobackend.entity.enums.LeaveType;
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

    private final LocalDateTime dateOfStart;

    private final LocalDateTime dateOfEnd;

    private final LocalDateTime dateOfReturn;

    private final String description;
}
