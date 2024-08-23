package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;
import org.ecommerce.Enums.ShippingStatusType;

import java.util.Currency;
import java.util.Date;

@Getter
@Setter
public class ShippingInformation extends AddressInformation {

    private Long trackingNumber;
    private ShippingStatusType shippingStatus;
    private Currency shippingCost;
    private Date estimatedDeliveryDate;

    ShippingInformation(Long id) {
        super(id);
    }
}
