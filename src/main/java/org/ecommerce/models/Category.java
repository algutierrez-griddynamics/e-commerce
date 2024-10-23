package org.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ecommerce.enums.CategoryType;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
@AttributeOverride(name="id", column=@Column(name="pk_category_id"))
public class Category extends Identity {
    @NotNull @Column(nullable = false)
    private String name;
    private String description;
    @NotNull @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

}
