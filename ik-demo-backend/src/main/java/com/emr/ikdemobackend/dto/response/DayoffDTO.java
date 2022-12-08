package com.emr.ikdemobackend.dto.response;

import com.emr.ikdemobackend.entity.enums.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DayoffDTO {
    private final Long id;

    private final int employeeNationalId;

    private final LeaveType leaveType;

    private final int daysOfLeave;

    private final LocalDateTime dateOfStart;

    private final LocalDateTime dateOfEnd;

    private final LocalDateTime dateOfReturn;

    private final String description;
}
