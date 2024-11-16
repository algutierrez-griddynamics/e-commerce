package org.ecommerce.services.jpa;

import org.ecommerce.models.PaymentDetails;
import org.ecommerce.services.OperationsService;

public interface PaymentDetailsI extends OperationsService<PaymentDetails, Long> {
    boolean validateData(PaymentDetails paymentDetails);
}
