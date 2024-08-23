package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Currency;
import java.util.Date;

@Getter
@Setter
public class BillingInformation extends AddressInformation {

    private Date billingDate;
    private Currency amount;

    BillingInformation(Long id) {
        super(id);
    }
}
