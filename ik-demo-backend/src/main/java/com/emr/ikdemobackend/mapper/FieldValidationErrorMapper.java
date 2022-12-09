package com.emr.ikdemobackend.mapper;

import com.emr.ikdemobackend.exception.error.FieldValidationError;
import jakarta.validation.ConstraintViolation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "FieldValidationErrorMapperImpl" , componentModel = "spring")
public interface FieldValidationErrorMapper {

    @Mapping(target = "field", expression = "java(constraintViolation.getPropertyPath().toString())")
    FieldValidationError toFieldValidationErrorFromConstraintViolation(ConstraintViolation<?> constraintViolation);
}
