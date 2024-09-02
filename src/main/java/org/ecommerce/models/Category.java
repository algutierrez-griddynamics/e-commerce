package org.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Category extends Identity {
    private String name;
    private String description;
    private Category categoryType;


}
