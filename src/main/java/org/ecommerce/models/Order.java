package org.ecommerce.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.ecommerce.enums.OrderStatus;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orders")
@AttributeOverride(name="id", column=@Column(name="pk_order_id"))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Order extends Identity {
    @JoinColumn(name = "fk_customer_id", nullable = true)
    @ManyToOne
    private Customer customer;

    @Column(name = "date", nullable = true)
    @NotNull
    private Date orderDate;

    @Column(name = "total_usd", nullable = true)
    @NotNull
    private BigDecimal totalUsd;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "products_orders")
    @NotNull
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = true, length = 10)
    private OrderStatus status;

    @JoinColumn(name = "fk_shipping_information_id", nullable = true)
    @ManyToOne
    @NotNull
    private ShippingInformation shippingInformation;

    @JoinColumn(name = "fk_billing_information_id", nullable = true)
    @ManyToOne
    @NotNull
    private BillingInformation billingInformation;

    @JoinColumn(name = "fk_payment_details_id", nullable = true)
    @ManyToOne
    @NotNull
    private PaymentDetails paymentDetails;
}
