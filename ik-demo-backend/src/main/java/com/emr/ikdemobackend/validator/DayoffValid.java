package com.emr.ikdemobackend.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DayoffDatesValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DayoffValid {
    String message() default "Please check dates.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
