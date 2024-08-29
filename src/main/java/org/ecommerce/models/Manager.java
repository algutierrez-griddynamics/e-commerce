package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class Manager extends User {
    private int employeeNumber;

    public Manager(Long id, String firstName, String lastName, String email, String password, int employeeNumber) {
        super(id, firstName, lastName, email, password);
        this.employeeNumber = employeeNumber;
    }
}
