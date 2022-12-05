package com.emr.ikdemobackend.dto.response;

import com.emr.ikdemobackend.entity.EmployeeAddress;
import com.emr.ikdemobackend.entity.enums.Levels;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class EmployeeDTO {
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
    private final EmployeeAddressDTO address;
}
