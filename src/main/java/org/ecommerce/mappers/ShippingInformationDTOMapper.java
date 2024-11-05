package org.ecommerce.mappers;

import org.ecommerce.dtos.responses.ShippingInformationDTO;
import org.ecommerce.models.ShippingInformation;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ShippingInformationDTOMapper implements Function<ShippingInformation, ShippingInformationDTO> {
    @Override
    public ShippingInformationDTO apply(ShippingInformation shippingInformation) {
        return null;
    }
}
