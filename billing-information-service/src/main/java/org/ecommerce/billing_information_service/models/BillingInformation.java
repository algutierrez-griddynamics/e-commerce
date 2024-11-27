package org.ecommerce.billing_information_service.models;

import java.util.Date;

public class BillingInformation extends AddressInformation {
    private Long orderId;
    private Date billingDate;
    private Price amount;

    public BillingInformation(){}

    public BillingInformation(long billingId) {
        super(billingId);
    }

    public BillingInformation(Long id, String street, String city, String state, String zipCode, String country, long orderId, Date billingDate, Price amount) {
        super(id, street, city, state, zipCode, country);
        this.orderId = orderId;
        this.billingDate = billingDate;
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public Price getAmount() {
        return amount;
    }
}
