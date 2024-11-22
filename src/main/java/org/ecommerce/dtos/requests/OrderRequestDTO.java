package org.ecommerce.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.ecommerce.enums.OrderStatus;
import org.ecommerce.validations.constraints.CurrentDay;
import org.ecommerce.validations.constraints.ValidOrderStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OrderRequestDTO (

        @NotNull
        @ValidOrderStatus// -> Is this validation actually useful?
        OrderStatus status,

        @CurrentDay
        @NotNull
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
        @NotNull
        Date date,

        @PositiveOrZero
        @NotNull
        BigDecimal totalUsd,

        @NotNull
        List<Long> productsIds,

        @NotNull
        @Positive
        Long fk_customer_id,

        @NotNull
        @Positive
        Long fk_billing_information_id,

        @NotNull
        @Positive
        Long fk_shipping_information_id,

        @NotNull
        @Positive
        Long fk_payment_details_id
) {
}
