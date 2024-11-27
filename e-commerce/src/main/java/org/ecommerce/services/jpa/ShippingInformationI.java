package org.ecommerce.services.jpa;

import org.ecommerce.models.ShippingInformation;
import org.ecommerce.services.OperationsService;

public interface ShippingInformationI extends OperationsService <ShippingInformation, Long> {
    boolean validateShippingInformation(ShippingInformation shippingInformation);
}
