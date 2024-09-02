package org.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Currency;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
public class BillingInformation extends AddressInformation {

    private Long orderId;
    private Date billingDate;
    private Currency amount;

}
