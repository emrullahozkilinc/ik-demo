package com.emr.ikdemobackend.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
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
    @PastOrPresent(message = "Date must be past or present.")
    private final LocalDateTime date;
    @Min(value = 1, message = "Hours must be greater than 1.")
    @Max(value = 8, message = "Hours must be smaller than 8.")
    private final int hours;
    @NotBlank(message = "Description cannot be empty.")
    private final String description;
}
