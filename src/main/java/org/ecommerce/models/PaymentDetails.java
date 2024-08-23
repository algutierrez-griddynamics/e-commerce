package org.ecommerce.models;

import org.ecommerce.Enums.PaymentMethodType;

import java.util.Currency;
import java.util.Date;

public class PaymentDetails extends Identity {
    private PaymentMethodType paymentMethodType;
    private String cardNumber;
    private Date cardExpirationDate;
    private String cardHolderName;
    private String cardCVV;
    private Currency billingAmount;
    private Date billingDate;


    PaymentDetails(Long id, PaymentMethodType paymentMethodType, String cardNumber, Date cardExpirationDate, String cardCVV
            , String cardHolderName, Currency billingAmount, Date billingDate) {
        super(id);
        this.paymentMethodType = paymentMethodType;
        this.cardNumber = cardNumber;
        this.cardExpirationDate = cardExpirationDate;
        this.cardCVV = cardCVV;
        this.cardHolderName = cardHolderName;
        this.billingAmount = billingAmount;
        this.billingDate = billingDate;
    }
}
