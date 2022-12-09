package com.emr.ikdemobackend.validator;

import com.emr.ikdemobackend.entity.Dayoff;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DayoffDatesValidator implements ConstraintValidator<DayoffValid, Dayoff> {

    @Override
    public boolean isValid(Dayoff dayoff, ConstraintValidatorContext constraintValidatorContext) {
        boolean isEndDateAfterStartDate = dayoff.getDateOfEnd().isAfter(dayoff.getDateOfStart());
        boolean isReturnDateAfterOrEqualEndDate =
                dayoff.getDateOfReturn().isAfter(dayoff.getDateOfEnd()) || dayoff.getDateOfReturn().isEqual(dayoff.getDateOfEnd());

        return isEndDateAfterStartDate && isReturnDateAfterOrEqualEndDate;
    }
}
