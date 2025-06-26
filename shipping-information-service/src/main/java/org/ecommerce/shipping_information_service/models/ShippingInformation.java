package org.ecommerce.shipping_information_service.models;

import org.ecommerce.shipping_information_service.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

public class ShippingInformation extends AddressInformation {
    private Long trackingNumber;
    private OrderStatus shippingStatus;
    private Price shippingCost;
    private Date estimatedDeliveryDate;

    public ShippingInformation(Long shippingInformationId) {
        super(shippingInformationId);
        this.shippingCost = new Price(1001L, Currency.getInstance("MXN"), new BigDecimal("29.99"));
    }

    public Price getShippingCost() {
        return shippingCost;
    }
}

