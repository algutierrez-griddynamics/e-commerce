package org.ecommerce.mappers;

import org.ecommerce.dtos.responses.BillingInformationDTO;
import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.MappingException;
import org.ecommerce.models.BillingInformation;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class BillingInformationDtoMapper implements Function<BillingInformation, BillingInformationDTO> {
    @Override
    public BillingInformationDTO apply(BillingInformation billingInformation) {
        return Optional.ofNullable(billingInformation)
                .map(bi -> new BillingInformationDTO(
                        bi.getBillingDate(),
                        bi.getAmount()
                )).orElseThrow(
                        () -> new MappingException(getClass().getSimpleName() , Error.MAPPING_EXCEPTION.getDescription())
                );
    }
}
