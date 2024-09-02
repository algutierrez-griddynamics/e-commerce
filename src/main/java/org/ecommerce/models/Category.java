package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Category extends Identity {
    private String name;
    private String description;
    private Category categoryType;

    Category() {}

    Category(Long id, String name, String description, Category categoryType) {
        super(id);
        this.name = name;
        this.description = description;
        this.categoryType = categoryType;
    }

}
