package org.ecommerce.util.tests;

import org.ecommerce.enums.OrderStatus;
import org.ecommerce.models.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderUtils {
    public static Order buildOrder() {
        return Order.builder()
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.PLACED)
                .totalUsd(new BigDecimal("999999"))
                .products(new ArrayList<>())
                .customer(buildCustomer())
                .shippingInformation(buildShippingInformation())
                .billingInformation(buildBillingInformation())
                .paymentDetails(buildPaymentDetails())
                .build();
    }

    private static PaymentDetails buildPaymentDetails() {
        return PaymentDetails.builder().id(1L).build();
    }

    private static BillingInformation buildBillingInformation() {
        return BillingInformation.builder().id(1L).build();
    }

    private static ShippingInformation buildShippingInformation() {
        return ShippingInformation.builder().id(1L).build();
    }

    private static Customer buildCustomer() {
        return Customer.builder().id(1L).build();
    }
}
