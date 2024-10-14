package org.ecommerce.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.ecommerce.enums.OrderStatus;

import java.util.Date;
import java.util.List;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Order extends Identity {
    @ManyToOne
    private Customer customer;

    private Date orderDate;

    @ManyToMany
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne
    private ShippingInformation shippingInformation;

    @OneToOne
    private BillingInformation billingInformation;

    @OneToOne
    private PaymentDetails paymentDetails;
}
