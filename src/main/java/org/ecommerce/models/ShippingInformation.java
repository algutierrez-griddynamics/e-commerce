package org.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ecommerce.enums.OrderStatus;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingInformation extends AddressInformation {

    private Long trackingNumber;
    private OrderStatus shippingStatus;
    private Price shippingCost;
    private Date estimatedDeliveryDate;
}
