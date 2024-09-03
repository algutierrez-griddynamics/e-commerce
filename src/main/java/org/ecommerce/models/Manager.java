package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class Manager extends User {
    private int employeeNumber;
}
