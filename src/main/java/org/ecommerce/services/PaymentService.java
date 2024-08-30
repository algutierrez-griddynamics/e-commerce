package org.ecommerce.services;

import org.ecommerce.logs.Log;
import org.ecommerce.models.PaymentDetails;

import java.util.Map;

public class PaymentService extends Log {

    public Map<Boolean, String> processPayment(PaymentDetails paymentDetails) {
        Log.info("Processing payment: " + paymentDetails);
        return Map.of(Boolean.TRUE, "Payment processed successfully");
    }

}
