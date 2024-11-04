package org.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stock_entry")
@AttributeOverride(name="id", column=@Column(name="pk_stock_entry_id"))
public class StockEntry extends Identity {
    @NotNull @PrimaryKeyJoinColumn
    @ManyToOne
    @JoinColumn(name = "fk_product_id")
    private Product product;

    @NotNull @PrimaryKeyJoinColumn
    @ManyToOne
    @JoinColumn(name = "fk_location_id")
    private Location location;

    @NotNull @Column(nullable = false)
    private int stock;

    @NotNull @Column(nullable = false)
    private String measurement_unit;
}
