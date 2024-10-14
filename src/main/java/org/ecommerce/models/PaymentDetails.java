package org.ecommerce.models;

import jakarta.persistence.*;
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
@Entity
public class PaymentDetails extends Identity {
    @Enumerated(EnumType.STRING)
    private PaymentMethodType paymentMethodType;
    private String cardNumber;
    private Date cardExpirationDate;
    private String cardHolderName;
    private String cardCVV;
    @OneToOne
    private Price billingAmount;
    private Date billingDate;
}
