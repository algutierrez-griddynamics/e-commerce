package org.ecommerce.billing_information_service;

import org.ecommerce.billing_information_service.models.BillingInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.NonNull;

import java.time.LocalDateTime;

@RestController
@RequestMapping("billing-information")
public class BillingInformationController {

    @Autowired
    private Environment environment;

    @GetMapping(path = "/validate/{billingId}")
    public boolean validateBillingInformation(@PathVariable @NonNull String billingId) {
        return true;
    }

    @GetMapping(path = "/get/{billingId}")
    public BillingInformation getBillingInformation(@PathVariable @NonNull Long billingId) {
        System.out.println("Retrieving BillingInformation from port: " + environment.getProperty("local.server.port", "unknown")
        + " at " + LocalDateTime.now() + " ");
        return new BillingInformation(billingId);
    }


}
