package org.ecommerce.validations.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ecommerce.util.Enums;
import org.ecommerce.validations.constraints.OrderStatus;


public class OrderStatusValidator implements ConstraintValidator<OrderStatus, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Enums.isInEnum(value, org.ecommerce.enums.OrderStatus.class);
    }
}
