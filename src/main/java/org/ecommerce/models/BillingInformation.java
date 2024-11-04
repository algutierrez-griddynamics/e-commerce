package org.ecommerce.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "billing_information")
@AttributeOverride(name="id", column=@Column(name="pk_billing_information_id"))
@ToString
public class BillingInformation extends AddressInformation {
    @NotNull
    @Column(nullable = false)
    private Long orderId;
    @NotNull
    @Column(nullable = false)
    private Date billingDate;
    @NotNull
    @PrimaryKeyJoinColumn @ManyToOne
    @JoinColumn(name = "fk_price_id")
    private Price amount;
}
