package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;
import org.ecommerce.enums.OrderStatus;

import java.util.Date;

@Getter
@Setter
public class ShippingInformation extends AddressInformation {

    private Long trackingNumber;
    private OrderStatus shippingStatus;
    private Price shippingCost;
    private Date estimatedDeliveryDate;

    ShippingInformation() {}

    ShippingInformation(Long id) {
        super(id);
    }
}
