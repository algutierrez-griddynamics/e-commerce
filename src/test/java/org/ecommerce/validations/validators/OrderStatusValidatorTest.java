package org.ecommerce.validations.validators;

import org.ecommerce.enums.OrderStatus;
import org.ecommerce.util.Enums;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import jakarta.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.*;

public class OrderStatusValidatorTest {

    private final OrderStatusValidator validator = new OrderStatusValidator();

    @Mock
    private ConstraintValidatorContext context;

    @Test
    void testIsValid_withValidValue() {
        OrderStatus validStatus = OrderStatus.PLACED;

        boolean result = validator.isValid(validStatus, context);

        assertTrue(result);
    }

    @Test
    void testIsValid_withInvalidValue() {
        OrderStatus invalidStatus = null;

        assertThrows(NullPointerException.class,
                () -> validator.isValid(invalidStatus, context)
        );
    }

    @Test
    void testEnumsUtility_withValidAndInvalidValues() {
        String validValue = OrderStatus.PLACED.name();
        String invalidValue = OrderStatus.PLACED.name() + " wrong";

        boolean validResult = Enums.isInEnum(validValue, OrderStatus.class);
        boolean invalidResult = Enums.isInEnum(invalidValue, OrderStatus.class);

        assertAll(
                () -> {
                    assertTrue(validResult);
                    assertFalse(invalidResult);
                }
        );
    }
}
