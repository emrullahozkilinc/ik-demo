package com.emr.ikdemobackend.dto.response.history;

import com.emr.ikdemobackend.entity.enums.SpendingType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class SpendingHistoryDTO {
    private final Long id;
    private final Long employeeNationalId;
    private final SpendingType spendingType;
    private final BigDecimal amount;
    private final LocalDate receiptDate;
    private final int taxRate;
    private final String description;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
