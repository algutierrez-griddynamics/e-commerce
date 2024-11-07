package org.ecommerce.mappers;

import org.ecommerce.dtos.requests.OrderRequestDTO;
import org.ecommerce.models.*;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BuildOrderFromDTORequest implements Function<OrderRequestDTO, Order> {

    @Override
    public Order apply(OrderRequestDTO order) {
        return Order.builder()
            .status(order.status())
            .orderDate(order.date())
            .totalUsd(order.totalUsd())
            .customer(Customer.builder()
                    .id(order.fk_customer_id()).build())
            .billingInformation(BillingInformation.builder()
                    .id(order.fk_billing_information_id()).build())
            .shippingInformation(ShippingInformation.builder()
                    .id(order.fk_shipping_information_id()).build())
            .paymentDetails(PaymentDetails.builder()
                    .id(order.fk_shipping_information_id()).build()
            ).build();
    }
}
