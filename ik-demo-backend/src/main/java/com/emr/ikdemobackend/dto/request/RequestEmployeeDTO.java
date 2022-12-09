package com.emr.ikdemobackend.dto.request;

import com.emr.ikdemobackend.entity.enums.Levels;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestEmployeeDTO {
    @Size(min = 2, max = 128, message = "Name must be 2-128 characters long.")
    private final String firstName;
    @Size(min = 2, max = 128, message = "Lastname must be 2-128 characters long.")
    private final String lastName;
    @NotNull(message = "National Id cannot be empty.")
    private final Long nationalId;
    @NotBlank(message = "Position cannot be empty.")
    private final String position;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Wrong date.")
    @NotNull(message = "Start date cannot be empty.")
    private final LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Born date cannot be empty.")
    private final LocalDate bornDate;
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary cannot be zero or negative.")
    @DecimalMax(value = "300000.0", message = "Salary cannot be greater than 300.000 TL.")
    @NotNull(message = "Salary cannot be empty.")
    private final BigDecimal salary;
    private final Levels level;
    @NotBlank(message = "Title cannot be empty.")
    private final String title;
    @NotBlank(message = "Department cannot be empty.")
    private final String department;
    @Email(message = "Wrong email.")
    private final String email;
    @Pattern(regexp = "^(0)([0-9]{3})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$", message = "Wrong telephone.")
    private final String phone;
    private final RequestEmployeeAddressDTO address;
}
