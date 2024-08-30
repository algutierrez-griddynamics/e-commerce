package org.ecommerce.util.payments;

import org.ecommerce.logs.Log;
import org.ecommerce.models.PaymentDetails;

import java.util.Map;

public class PaymentService extends Log {

    public static Map<Boolean, String> processPayment(PaymentDetails paymentDetails) {
        Log.info("Processing payment: " + paymentDetails);
        return Map.of(Boolean.TRUE, "Payment processed successfully");
    }

}
