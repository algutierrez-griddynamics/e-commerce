package org.ecommerce.mappers;

import org.ecommerce.dtos.responses.ShippingInformationDTO;
import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.MappingException;
import org.ecommerce.models.ShippingInformation;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class ShippingInformationDTOMapper implements Function<ShippingInformation, ShippingInformationDTO> {
    @Override
    public ShippingInformationDTO apply(ShippingInformation shippingInformation) {
        return Optional.ofNullable(shippingInformation)
                .map(si -> new ShippingInformationDTO(
                        si.getStreet(),
                        si.getCity(),
                        si.getState(),
                        si.getZipCode(),
                        si.getCountry(),
                        si.getTrackingNumber(),
                        si.getShippingStatus(),
                        si.getShippingCost(),
                        si.getEstimatedDeliveryDate()
                ))
                .orElseThrow(
                        () -> new MappingException(getClass().getSimpleName() , Error.MAPPING_EXCEPTION.getDescription())
                );
    }
}
