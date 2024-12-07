package org.ecommerce.feign;

import io.github.resilience4j.retry.annotation.Retry;
import org.ecommerce.models.PaymentDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.util.annotation.NonNull;

@FeignClient("PAYMENT-DETAILS-SERVICE")
public interface PaymentInformationInterface extends FallbackHandler<PaymentDetails>{

    @Retry(name = "default", fallbackMethod = "handleBooleanMethodsFallback")
    @GetMapping(path = "payment-details/validate/{paymentId}")
    boolean validatePaymentDetails(@PathVariable @NonNull Long paymentId);

    @Retry(name = "default", fallbackMethod = "handleObjectMethodsFallback")
    @GetMapping(path = "payment-details/get/{paymentDetailsId}")
    PaymentDetails getPaymentDetails(@PathVariable @NonNull Long paymentDetailsId);
}
