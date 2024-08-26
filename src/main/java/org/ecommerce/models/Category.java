package org.ecommerce.models;

import org.ecommerce.util.CategoryType;

public class Category extends Identity {
    private String name;
    private String description;
    private CategoryType categoryType;

    Category(Long id, String name, String description, CategoryType categoryType) {
        super(id);
        this.name = name;
        this.description = description;
        this.categoryType = categoryType;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
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
