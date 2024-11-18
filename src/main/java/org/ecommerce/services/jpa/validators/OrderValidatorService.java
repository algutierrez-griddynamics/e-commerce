package org.ecommerce.services.jpa.validators;

import org.ecommerce.dtos.requests.OrderRequestDTO;
import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.BillingInformationException;
import org.ecommerce.exceptions.OutOfStockException;
import org.ecommerce.exceptions.PaymentDetailsException;
import org.ecommerce.exceptions.ShippingInformationException;
import org.ecommerce.models.BillingInformation;
import org.ecommerce.models.PaymentDetails;
import org.ecommerce.models.Product;
import org.ecommerce.models.ShippingInformation;
import org.ecommerce.services.ProductService;
import org.ecommerce.services.jpa.PaymentDetailsI;
import org.ecommerce.services.jpa.ShippingInformationI;
import org.ecommerce.services.jpa.StockServiceI;
import org.ecommerce.services.jpa.impl.BillingInformationI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderValidatorService {

    private final ProductService productService;

    private final BillingInformationI billingInformationService;
    private final PaymentDetailsI paymentDetailsService;
    private final StockServiceI stockService;
    private final ShippingInformationI shippingInformationService;


    public OrderValidatorService(ProductService productService, BillingInformationI billingInformationService,
                                  PaymentDetailsI paymentDetailsService,
                                  StockServiceI stockService,
                                  ShippingInformationI shippingInformationService) {
        this.productService = productService;

        this.billingInformationService = billingInformationService;
        this.paymentDetailsService = paymentDetailsService;
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
        return Optional.ofNullable(paymentDetailsService.findById(paymentDetailsId))
                .map(paymentDetailsService::validatePaymentDetails)
                .orElse(false);
    }

    private boolean validateBillingInformation(Long billingInformationId) {
        return Optional.ofNullable(billingInformationService.findById(billingInformationId))
                .map(billingInformation -> billingInformationService.validateBillingInformation())
                .orElse(false);
    }

    private boolean validateShippingInformation(Long shippingInformationId) {
        return Optional.ofNullable(shippingInformationService.findById(shippingInformationId))
                .map(shippingInformationService::validateShippingInformation)
                .orElse(false);
    }

    private boolean checkStock(List<Long> productsIds, Map<Long, Long> quantityOfEachProductForId) {
        return productsIds.stream()
                .allMatch(productId -> Optional.ofNullable(productService.findById(productId))
                        .map(product -> stockService.getStockOfProduct(product) >= quantityOfEachProductForId.get(productId))
                        .orElse(false));
    }

}
