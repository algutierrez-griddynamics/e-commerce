package org.ecommerce.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employees")
@AttributeOverride(name="id", column=@Column(name="pk_manager_id"))
public class Employee extends User {
    @NotNull @Column(nullable = false)
    private int employeeNumber;
    @NotNull @Column(nullable = false)
    private String role;
}
