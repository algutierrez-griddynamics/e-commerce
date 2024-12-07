package org.ecommerce.feign;

import io.github.resilience4j.retry.annotation.Retry;
import org.ecommerce.models.BillingInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;

@FeignClient(value = "BILLING-INFORMATION-SERVICE")
public interface BillingInformationInterface extends FallbackHandler<BillingInformation> {

    @Retry(name = "default", fallbackMethod = "handleBooleanMethodsFallback")
    @GetMapping("billing-information/validate/{billingId}")
    boolean validateBillingInformation(@PathVariable @NotNull Long billingId);

    @Retry(name = "default", fallbackMethod = "handleObjectMethodsFallback")
    @GetMapping("billing-information/get/{billingId}")
    BillingInformation getBillingInformation(@PathVariable @NotNull Long billingId);
}
