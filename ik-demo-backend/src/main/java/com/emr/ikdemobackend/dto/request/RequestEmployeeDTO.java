package com.emr.ikdemobackend.dto.request;

import com.emr.ikdemobackend.entity.enums.Levels;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestEmployeeDTO {
    private final String firstName;
    private final String lastName;
    private final Long nationalId;
    private final String position;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate bornDate;
    private final BigDecimal salary;
    private final Levels level;
    private final String title;
    private final String department;
    private final String email;
    private final String phone;
    private final RequestEmployeeAddressDTO address;
}
