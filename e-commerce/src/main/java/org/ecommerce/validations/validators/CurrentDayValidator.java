package org.ecommerce.validations.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ecommerce.validations.constraints.CurrentDay;

import java.time.LocalDateTime;

public class CurrentDayValidator implements ConstraintValidator<CurrentDay, LocalDateTime> {
    @Override
    public boolean isValid(LocalDateTime date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();

        return date.toLocalDate().equals(now.toLocalDate());
    }
}
