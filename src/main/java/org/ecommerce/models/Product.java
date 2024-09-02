package org.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Currency;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Product extends Identity {
    private Long inventoryId;
    private String name;
    private Currency price;
    private String description;
    private List<Category> categories;

}
