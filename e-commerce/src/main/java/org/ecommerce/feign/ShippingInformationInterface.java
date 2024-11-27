package org.ecommerce.feign;

import org.ecommerce.models.ShippingInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.util.annotation.NonNull;

@FeignClient("SHIPPING-INFORMATION-SERVICE")
public interface ShippingInformationInterface {
    @GetMapping(path = "shipping-information/get/{shippingInformationId}")
    ShippingInformation getShippingInformation(@PathVariable @NonNull Long shippingInformationId);

    @GetMapping(path = "shipping-information/validate/{shippingInformationId}")
    boolean validateShippingInformation(@PathVariable @NonNull Long shippingInformationId);
}
