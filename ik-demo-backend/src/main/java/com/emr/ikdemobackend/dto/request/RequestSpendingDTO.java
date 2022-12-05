package com.emr.ikdemobackend.dto.request;

import com.emr.ikdemobackend.entity.enums.SpendingType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestSpendingDTO {
    private final int employeeNationalId;
    private final SpendingType spendingType;
    private final BigDecimal amount;
    private final LocalDate receiptDate;
    private final int taxRate;
    private final String description;
}