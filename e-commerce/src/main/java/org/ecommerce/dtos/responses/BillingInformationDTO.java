package org.ecommerce.dtos.responses;

import org.ecommerce.models.Price;

import java.util.Date;

public record BillingInformationDTO (
        Date billingDate,
        Price amount
) {
}
