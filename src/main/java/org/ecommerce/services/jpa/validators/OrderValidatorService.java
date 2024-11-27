package org.ecommerce.services.jpa.validators;

import org.ecommerce.dtos.requests.OrderRequestDTO;
import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.BillingInformationException;
import org.ecommerce.exceptions.OutOfStockException;
import org.ecommerce.exceptions.PaymentDetailsException;
import org.ecommerce.exceptions.ShippingInformationException;
import org.ecommerce.feign.BillingInformationInterface;
import org.ecommerce.feign.PaymentInformationInterface;
import org.ecommerce.feign.ShippingInformationInterface;
import org.ecommerce.services.ProductService;
import org.ecommerce.services.jpa.StockServiceI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderValidatorService {

    private final ProductService productService;

    private final BillingInformationInterface billingInformationService;
    private final PaymentInformationInterface paymentInformationService;
    private final ShippingInformationInterface shippingInformationService;

    private final StockServiceI stockService;

    public OrderValidatorService(ProductService productService, BillingInformationInterface billingInformationService,
                                  PaymentInformationInterface paymentInformationService,
                                  StockServiceI stockService,
                                  ShippingInformationInterface shippingInformationService) {
        this.productService = productService;

        this.billingInformationService = billingInformationService;
        this.paymentInformationService = paymentInformationService;
        this.stockService = stockService;
        this.shippingInformationService = shippingInformationService;
    }

    public void validateOrder(OrderRequestDTO order, Map<Long, Long> quantityOfEachProductForId){
        if (!validateBillingInformation(order.fk_billing_information_id())) {
            throw new BillingInformationException(Error.INVALID_BILLING_INFORMATION.getDescription());
        }
        if (!validatePaymentDetails(order.fk_payment_details_id())) {
            throw new PaymentDetailsException(Error.PAYMENT_DECLINED.getDescription());
        }
        if (!checkStock(order.productsIds(), quantityOfEachProductForId)) {
            throw new OutOfStockException(Error.OUT_OF_STOCK.getDescription());
        }
        if (!validateShippingInformation(order.fk_shipping_information_id())) {
            throw new ShippingInformationException(Error.INVALID_SHIPPING_INFORMATION.getDescription());
        }
    }

    private boolean validatePaymentDetails(Long paymentDetailsId) {
        return Optional.ofNullable(paymentInformationService.getPaymentDetails(paymentDetailsId))
                .map(paymentDetails -> paymentInformationService.validatePaymentDetails(paymentDetails.getId()))
                .orElse(false);
    }

    private boolean validateBillingInformation(Long billingInformationId) {
        return Optional.ofNullable(billingInformationService.getBillingInformation(billingInformationId))
                .map(billingInformation -> billingInformationService.validateBillingInformation(billingInformation.getId()))
                .orElse(false);
    }

    private boolean validateShippingInformation(Long shippingInformationId) {
        return Optional.ofNullable(shippingInformationService.getShippingInformation(shippingInformationId))
                .map(shippingInformation -> shippingInformationService.validateShippingInformation(shippingInformation.getId()))
                .orElse(false);
    }

    private boolean checkStock(List<Long> productsIds, Map<Long, Long> quantityOfEachProductForId) {
        return productsIds.stream()
                .allMatch(productId -> Optional.ofNullable(productService.findById(productId))
                        .map(product -> stockService.getStockOfProduct(product) >= quantityOfEachProductForId.get(productId))
                        .orElse(false));
    }

}
