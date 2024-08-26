package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Currency;
import java.util.List;

@Setter
@Getter
public class Order extends Identity {
    private Long customerId;
    private Date orderDate;
    private String status;
    private List<Product> products;
    private ShippingInformation shippingInformation;
    private BillingInformation billingInformation;
    private PaymentDetails paymentDetails;

    Order(Long id, Long customerId, Date orderDate, String status, ShippingInformation shippingInformation, BillingInformation billingInformation, PaymentDetails paymentDetails, List<Product> products) {
        super(id);
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.shippingInformation = shippingInformation;
        this.billingInformation = billingInformation;
        this.paymentDetails = paymentDetails;
        this.products = products;
    }
}
