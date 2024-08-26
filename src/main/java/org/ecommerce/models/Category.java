package org.ecommerce.models;

import org.ecommerce.enums.Error;

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

    public Error getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Error categoryType) {
        this.categoryType = categoryType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
