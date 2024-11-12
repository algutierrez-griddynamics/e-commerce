package org.ecommerce.validations.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.time.DateUtils;
import org.ecommerce.validations.constraints.CurrentDay;

import java.util.Calendar;
import java.util.Date;

public class CurrentDayValidator implements ConstraintValidator<CurrentDay, Date> {
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) {
            return false;
        }
        Date now = Calendar.getInstance().getTime();
        return DateUtils.isSameDay(date, now);
    }
}
