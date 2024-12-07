package org.ecommerce.feign;

import io.github.resilience4j.retry.annotation.Retry;
import org.ecommerce.models.ShippingInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.util.annotation.NonNull;

@FeignClient("SHIPPING-INFORMATION-SERVICE")
public interface ShippingInformationInterface extends FallbackHandler<ShippingInformation> {

    @Retry(name = "default", fallbackMethod = "handleBooleanMethodsFallback")
    @GetMapping(path = "shipping-information/get/{shippingInformationId}")
    ShippingInformation getShippingInformation(@PathVariable @NonNull Long shippingInformationId);

    @Retry(name = "default", fallbackMethod = "handleObjectMethodsFallback")
    @GetMapping(path = "shipping-information/validate/{shippingInformationId}")
    boolean validateShippingInformation(@PathVariable @NonNull Long shippingInformationId);
}
