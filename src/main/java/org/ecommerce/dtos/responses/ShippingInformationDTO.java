package org.ecommerce.dtos.responses;

import org.ecommerce.enums.OrderStatus;
import org.ecommerce.models.Price;

import java.util.Date;

public record ShippingInformationDTO (
        String street,
        String city,
        String state,
        String zipCode,
        String country,

        Long trackingNumber,
        OrderStatus orderStatus,
        Price shippingPrice,
        Date estimatedDeliveryDate
) {
}
