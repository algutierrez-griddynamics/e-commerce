package org.ecommerce.dtos.responses;

import org.ecommerce.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record OrderDTO (
        CustomerDTO customer,
        Date orderDate,
        BigDecimal totalUsd,
        List<ProductDTO> products,
        OrderStatus status,
        ShippingInformationDTO shippingInformation,
        BillingInformationDTO billingInformation,
        PaymentDetailsDTO paymentDetails
) {
}
