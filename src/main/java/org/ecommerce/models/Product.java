package org.ecommerce.models;

import java.util.Currency;
import java.util.List;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getPrice() {
        return price;
    }

    public void setPrice(Currency price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
