package com.emr.ikdemobackend.dto.response.history;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DayoffHistoryDTO {
    private final Long id;
    private final int employeeNationalId;
    private final String leaveType;
    private final int daysOfLeave;
    private final LocalDateTime dateOfStart;
    private final LocalDateTime dateOfEnd;
    private final LocalDateTime dateOfReturn;
    private final String description;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
