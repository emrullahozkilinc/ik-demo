package com.emr.ikdemobackend.mapper;


import com.emr.ikdemobackend.exception.error.FieldValidationError;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.validation.FieldError;

@Mapper(implementationName = "MethodArgumentErrorMapperImpl" , componentModel = "spring")
public interface MethodArgumentErrorMapper {

    @Mapping(target = "value", expression = "java(fieldError.getRejectedValue().toString())")
    @Mapping(target = "message", expression = "java(fieldError.getDefaultMessage())")
    FieldValidationError toFieldValidationErrorFromFieldError(FieldError fieldError);
}
