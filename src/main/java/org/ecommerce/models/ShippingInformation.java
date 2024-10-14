package org.ecommerce.models;

import jakarta.persistence.*;
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
@Entity
public class ShippingInformation extends AddressInformation {

    private Long trackingNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus shippingStatus;

    @ManyToOne
    private Price shippingCost;
    private Date estimatedDeliveryDate;
}
