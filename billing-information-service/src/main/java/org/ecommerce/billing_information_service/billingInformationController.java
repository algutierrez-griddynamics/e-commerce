package org.ecommerce.billing_information_service;

import org.ecommerce.billing_information_service.models.BillingInformation;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.NonNull;

@RestController
@RequestMapping("billing-information")
public class billingInformationController {

    @GetMapping(path = "/validate/{billingId}")
    public boolean validateBillingInformation(@PathVariable @NonNull String billingId) {
        return true;
    }

    @GetMapping(path = "/get/{billingId}")
    public BillingInformation getBillingInformation(@PathVariable @NonNull Long billingId) {
        return new BillingInformation(billingId);
    }


}
