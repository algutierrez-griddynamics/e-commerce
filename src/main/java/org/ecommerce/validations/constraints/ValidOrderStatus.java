package org.ecommerce.validations.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.ecommerce.validations.validators.OrderStatusValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(value = {FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = OrderStatusValidator.class)
@Documented
public @interface ValidOrderStatus {
    String message() default "The status of the order should be part of the available registered orderStatus";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
