package org.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ecommerce.enums.OrderStatus;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipping_information")
@AttributeOverride(name="id", column=@Column(name="pk_shipping_information_id"))
public class ShippingInformation extends AddressInformation {

    @NotNull @Column(nullable = false)
    private Long trackingNumber;

    @NotNull @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "status")
    private OrderStatus shippingStatus;

    @NotNull @PrimaryKeyJoinColumn
    @ManyToOne
    @JoinColumn(name = "fk_price_id")
    private Price shippingCost;
    private Date estimatedDeliveryDate;
}
