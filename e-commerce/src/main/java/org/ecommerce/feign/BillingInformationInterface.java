package org.ecommerce.feign;

import org.ecommerce.models.BillingInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;

@FeignClient("BILLING-INFORMATION-SERVICE")
public interface BillingInformationInterface {

    @GetMapping("billing-information/validate/{billingId}")
    boolean validateBillingInformation(@PathVariable @NotNull Long billingId);

    @GetMapping("billing-information/get/{billingId}")
    BillingInformation getBillingInformation(@PathVariable @NotNull Long billingId);
}
