package org.ecommerce.billing_information_service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public boolean validateBillingInformation(@PathVariable @NonNull String billingId) {
        return true;
    }

    @GetMapping(path = "/get/{billingId}")
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public BillingInformation getBillingInformation(@PathVariable @NonNull Long billingId) {
        System.out.println("Retrieving BillingInformation from port: " + environment.getProperty("local.server.port", "unknown")
        + " at " + LocalDateTime.now() + " ");
        return new BillingInformation(billingId);
    }

    @GetMapping(path = "test-resilience")
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String unavailableMethod() {
        System.out.println("Hit unavailable method");
        throw new RuntimeException("Method called");
    }

    public String fallbackMethod(Throwable throwable) {
        System.out.println("Hit fallback method");
        return "Service unavailable";
    }

}
