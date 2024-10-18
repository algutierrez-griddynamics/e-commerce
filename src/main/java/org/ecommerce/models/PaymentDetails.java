package org.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ecommerce.enums.PaymentMethodType;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_details")
@AttributeOverride(name="id", column=@Column(name="pk_payment_details_id"))
public class PaymentDetails extends Identity {
    @NotNull @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethodType paymentMethodType;
    @NotNull @Column(nullable = false)
    private String cardNumber;
    @NotNull @Column(nullable = false)
    private Date cardExpirationDate;
    @NotNull @Column(nullable = false)
    private String cardHolderName;
    @NotNull @Column(nullable = false)
    private String cardCVV;
    @NotNull @PrimaryKeyJoinColumn
    @OneToOne
    @JoinColumn(name = "fk_billing_amount_id")
    private Price billingAmount;
    @NotNull @Column(nullable = false)
    private Date billingDate;
}
