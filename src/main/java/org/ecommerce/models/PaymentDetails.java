package org.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ecommerce.enums.PaymentMethodType;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails extends Identity {
    private PaymentMethodType paymentMethodType;
    private String cardNumber;
    private Date cardExpirationDate;
    private String cardHolderName;
    private String cardCVV;
    private Price billingAmount;
    private Date billingDate;
}
