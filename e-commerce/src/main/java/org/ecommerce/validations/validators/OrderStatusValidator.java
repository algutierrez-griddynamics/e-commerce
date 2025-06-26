package org.ecommerce.validations.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ecommerce.enums.OrderStatus;
import org.ecommerce.util.Enums;
import org.ecommerce.validations.constraints.ValidOrderStatus;


public class OrderStatusValidator implements ConstraintValidator<ValidOrderStatus, OrderStatus> {

    @Override
    public boolean isValid(OrderStatus value, ConstraintValidatorContext context) {
        return Enums.isInEnum(value.name(), org.ecommerce.enums.OrderStatus.class);
    }
}
