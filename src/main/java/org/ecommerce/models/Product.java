package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Currency;
import java.util.List;

@Setter
@Getter
public class Product extends Identity {
    private String name;
    private Currency price;
    private Integer stock;
    private String description;
    private List<Category> categories;

    Product(Long id, String name, Currency price, Integer stock, String description, List<Category> categories) {
        super(id);
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.categories = categories;
    }

}
