package org.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends User {
    private String phoneNumber;
    private String address;
    private List<String> preferences;
}
