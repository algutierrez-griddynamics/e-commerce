package org.ecommerce.payment_details_service;

import org.ecommerce.payment_details_service.models.PaymentDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.util.annotation.NonNull;

@RestController
@RequestMapping("payment-details")
public class paymentDetailsController {

    @GetMapping(path = "/validate/{paymentId}")
    public boolean validatePaymentDetails(@PathVariable @NonNull Long paymentId) {
        return true;
    }

    @GetMapping(path = "/get/{paymentDetailsId}")
    public PaymentDetails getPaymentDetails(@PathVariable @NonNull Long paymentDetailsId) {
        return new PaymentDetails(paymentDetailsId);
    }
}
