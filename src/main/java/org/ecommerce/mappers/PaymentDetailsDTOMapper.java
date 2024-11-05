package org.ecommerce.mappers;

import org.ecommerce.dtos.responses.PaymentDetailsDTO;
import org.ecommerce.models.PaymentDetails;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PaymentDetailsDTOMapper implements Function<PaymentDetails, PaymentDetailsDTO> {

    @Override
    public PaymentDetailsDTO apply(PaymentDetails paymentDetails) {
        return null;
    }
}
