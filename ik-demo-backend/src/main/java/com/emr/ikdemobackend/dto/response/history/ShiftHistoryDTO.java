package com.emr.ikdemobackend.dto.response.history;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ShiftHistoryDTO {
    private final Long id;
    private final Long employeeNationalId;
    private final LocalDateTime date;
    private final int hours;
    private final String description;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
