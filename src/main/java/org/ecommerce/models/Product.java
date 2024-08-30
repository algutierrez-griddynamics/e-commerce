package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Currency;
import java.util.List;

@Setter
@Getter
public class Product extends Identity {
    private Long inventoryId;
    private String name;
    private Currency price;
    private String description;
    private List<Category> categories;

    Product(Long id, Long inventoryId, String name, Currency price, String description, List<Category> categories) {
        super(id);
        this.inventoryId = inventoryId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.categories = categories;
    }

}
