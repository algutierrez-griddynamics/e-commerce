package org.ecommerce.dtos.responses;

import org.ecommerce.enums.PaymentMethodType;

public record PaymentDetailsDTO (
        PaymentMethodType paymentMethodType,
        String cardNumberFourLastDigits,
        String cardHolderName
) {
}
