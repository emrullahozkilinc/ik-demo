package com.emr.ikdemobackend.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmployeeAddressDTO {
    private final String address;
    private final String city;
    private final String country;
    private final int postalCode;
}
