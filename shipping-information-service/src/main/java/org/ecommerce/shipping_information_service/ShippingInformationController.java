package org.ecommerce.shipping_information_service;

import org.ecommerce.shipping_information_service.models.ShippingInformation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.util.annotation.NonNull;

@RestController
@RequestMapping("shipping-information")
public class ShippingInformationController {

    @GetMapping(path = "/get/{shippingInformationId}")
    public ShippingInformation getShippingInformation(@PathVariable @NonNull Long shippingInformationId) {
        return new ShippingInformation(shippingInformationId);
    }

    @GetMapping(path = "validate/{shippingInformationId}")
    public boolean validateShippingInformation(@PathVariable @NonNull Long shippingInformationId) {
        return true;
    }

}
