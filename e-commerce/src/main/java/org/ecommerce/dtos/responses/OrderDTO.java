package org.ecommerce.dtos.responses;

import org.ecommerce.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO (
        CustomerDTO customer,
        LocalDateTime orderDate,
        BigDecimal totalUsd,
        List<ProductDTO> products,
        OrderStatus status,
        ShippingInformationDTO shippingInformation,
        BillingInformationDTO billingInformation,
        PaymentDetailsDTO paymentDetails
) {
}
