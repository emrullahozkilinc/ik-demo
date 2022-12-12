package com.emr.ikdemobackend.dto.response.history;

import com.emr.ikdemobackend.dto.response.EmployeeAddressDTO;
import com.emr.ikdemobackend.entity.enums.Levels;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class EmployeeHistoryDTO {
    private final String firstName;
    private final String lastName;
    private final Long nationalId;
    private final String position;
    private final LocalDate startDate;
    private final LocalDate bornDate;
    private final BigDecimal salary;
    private final Levels level;
    private final String title;
    private final String department;
    private final String email;
    private final String phone;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final EmployeeAddressDTO address;
}
