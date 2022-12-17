package com.emr.ikdemobackend.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestDayoffDTO {

    private final Long employeeNationalId;
    @NotBlank(message = "Leave Type cannot be empty.")
    private final String leaveType;
    @Min(value = 1, message = "Leave days must be greater than 0")
    @Max(value = 360, message = "Leave days must be smaller than 360.")
    private final int daysOfLeave;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FutureOrPresent(message = "Start date must be future or present.")
    @NotNull(message = "Start date cannot be empty.")
    private final LocalDateTime dateOfStart;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "End date cannot be empty.")
    private final LocalDateTime dateOfEnd;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Return date cannot be empty.")
    private final LocalDateTime dateOfReturn;
    @NotBlank(message = "Description cannot be blank.")
    private final String description;
}
