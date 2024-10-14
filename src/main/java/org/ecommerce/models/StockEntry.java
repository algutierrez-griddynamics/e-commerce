package org.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stock_entry")
public class StockEntry extends Identity {
    @ManyToOne
    private Product product;

    @ManyToOne
    private Location location;

    private int stock;
    private String measurement_unit;
}
