package org.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@AttributeOverride(name="id", column=@Column(name="pk_product_id"))
public class Product extends Identity {
    @NotNull @Column(nullable = false)
    private Long inventoryId;
    @NotNull @Column(nullable = false)
    private String name;
    @NotNull @PrimaryKeyJoinColumn
    @OneToOne
    @JoinColumn(name = "fk_price_id")
    private Price price;
    private String description;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "products_categories")
    private List<Category> categories;
}
