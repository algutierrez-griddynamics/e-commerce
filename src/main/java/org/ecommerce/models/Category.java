package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;
import org.ecommerce.enums.Error;

@Setter
@Getter
public class Category extends Identity {
    private String name;
    private String description;
    private Error categoryType;

    Category(Long id, String name, String description, Error categoryType) {
        super(id);
        this.name = name;
        this.description = description;
        this.categoryType = categoryType;
    }

}
