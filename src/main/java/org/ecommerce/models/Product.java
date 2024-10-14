package org.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends Identity {
    private Long inventoryId;
    private String name;
    @OneToOne
    private Price price;
    private String description;
    @ManyToMany
    private List<Category> categories;
}
