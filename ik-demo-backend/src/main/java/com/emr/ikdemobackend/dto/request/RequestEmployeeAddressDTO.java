package com.emr.ikdemobackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestEmployeeAddressDTO {
    @NotBlank(message = "Address cannot be empty.")
    private final String address;
    @NotBlank(message = "City cannot be empty.")
    private final String city;
    @NotBlank(message = "Country cannot be empty.")
    private final String country;
    @Positive(message = "Postal code cannot be negative")
    private final int postalCode;
}