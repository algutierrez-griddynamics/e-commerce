package org.ecommerce.dtos.requests;

import org.ecommerce.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;

public record OrderRequestDTO (
        OrderStatus status,
        Date date,
        BigDecimal totalUsd,
        Long fk_customer_id,
        Long fk_billing_information_id,
        Long fk_shipping_information_id,
        Long fk_payment_details_id
) {
}
