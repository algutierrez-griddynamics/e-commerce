package org.ecommerce.mappers;

import org.ecommerce.dtos.responses.BillingInformationDTO;
import org.ecommerce.models.BillingInformation;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BillingInformationDtoMapper implements Function<BillingInformation, BillingInformationDTO> {
    @Override
    public BillingInformationDTO apply(BillingInformation billingInformation) {
        return null;
    }
}
