package com.emr.ikdemobackend.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestEmployeeAddressDTO {
    private final String address;
    private final String city;
    private final String country;
    private final int postalCode;
}