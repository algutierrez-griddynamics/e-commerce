package org.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ecommerce.enums.CategoryType;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends Identity {
    private String name;
    private String description;
    private CategoryType categoryType;

}
