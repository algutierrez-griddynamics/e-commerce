package org.ecommerce.dtos.requests;

import org.ecommerce.enums.OrderStatus;
import org.ecommerce.validations.constraints.CurrentDay;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public record OrderRequestDTO (

        @org.ecommerce.validations.constraints.OrderStatus
        OrderStatus status,

        @CurrentDay
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
        @NotNull
        Date date,
        BigDecimal totalUsd,
        Long fk_customer_id,
        Long fk_billing_information_id,
        Long fk_shipping_information_id,
        Long fk_payment_details_id
) {
}
