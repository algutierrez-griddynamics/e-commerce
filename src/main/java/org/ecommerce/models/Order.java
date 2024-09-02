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

}
