package com.emr.ikdemobackend.dto.response;

import com.emr.ikdemobackend.entity.enums.SpendingType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class SpendingDTO {
    private final Long id;
    private final Long employeeNationalId;
    private final SpendingType spendingType;
    private final BigDecimal amount;
    private final LocalDate receiptDate;
    private final int taxRate;
    private final String description;
}
