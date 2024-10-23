package org.ecommerce.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@SuperBuilder
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "customers")
@AttributeOverride(name="id", column=@Column(name="pk_customer_id"))
public class Customer extends User {
    @NotNull @Column(nullable = false)
    private String phoneNumber;
    private String address;
    @Column(name = "categories_preferences", columnDefinition = "VARCHAR(100) ARRAY")
    private List<String> categoriesPreferences = new ArrayList<>();
}
