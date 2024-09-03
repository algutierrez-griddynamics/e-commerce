package org.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends Identity {
    private Long inventoryId;
    private String name;
    private Price price;
    private String description;
    private List<Category> categories;
}
