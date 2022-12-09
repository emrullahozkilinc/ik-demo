package com.emr.ikdemobackend.dto.request;

import com.emr.ikdemobackend.entity.enums.SpendingType;
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
public class RequestSpendingDTO {
    private final Long employeeNationalId;
    private final SpendingType spendingType;
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount cannot be zero or negative.")
    @DecimalMax(value = "100000.0", message = "Amount cannot be greater than 100.000 TL.")
    @NotNull(message = "Amount cannot be empty.")
    private final BigDecimal amount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Date must be past or present.")
    private final LocalDate receiptDate;
    private final int taxRate;
    @NotBlank(message = "Description cannot be empty.")
    private final String description;
}
