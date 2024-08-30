package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.ecommerce.enums.OrderStatus;

import java.sql.Date;
import java.util.List;

@SuperBuilder
@Setter
@Getter
public class Order extends Identity {
    private Long customerId;
    private Date orderDate;
    private List<Product> products;
    private OrderStatus status;
    private ShippingInformation shippingInformation;
    private BillingInformation billingInformation;
    private PaymentDetails paymentDetails;

    Order(Long id, Long customerId, Date orderDate,  List<Product> products, OrderStatus status, ShippingInformation shippingInformation, BillingInformation billingInformation, PaymentDetails paymentDetails) {
        super(id);
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.products = products;
        this.status = status;
        this.shippingInformation = shippingInformation;
        this.billingInformation = billingInformation;
        this.paymentDetails = paymentDetails;
    }
}
