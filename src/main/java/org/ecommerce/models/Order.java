package org.ecommerce.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.ecommerce.enums.OrderStatus;

import java.util.Date;
import java.util.List;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order extends Identity {
    private Customer customer;
    private Date orderDate;
    private List<Product> products;
    private OrderStatus status;
    private ShippingInformation shippingInformation;
    private BillingInformation billingInformation;
    private PaymentDetails paymentDetails;
}
