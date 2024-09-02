package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BillingInformation extends AddressInformation {

    private Long orderId;
    private Date billingDate;
    private Price amount;

    BillingInformation() {}

    BillingInformation(Long id) {
        super(id);
    }
}
