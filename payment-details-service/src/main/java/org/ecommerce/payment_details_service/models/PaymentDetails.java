package org.ecommerce.payment_details_service.models;

import org.ecommerce.payment_details_service.enums.PaymentMethodType;

import java.util.Date;

public class PaymentDetails extends Identity {
    private PaymentMethodType paymentMethodType;
    private String cardNumber;
    private Date cardExpirationDate;
    private String cardHolderName;
    private String cardCVV;
    private Price billingAmount;
    private Date billingDate;

    public PaymentDetails(Long paymentDetailsId) {
        super(paymentDetailsId);
    }
}

