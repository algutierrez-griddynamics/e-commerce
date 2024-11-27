package org.ecommerce.validations.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.ecommerce.validations.validators.CurrentDayValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(value = {FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CurrentDayValidator.class)
@Documented
public @interface CurrentDay {
    String message() default "The date entered should be the same as today";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
