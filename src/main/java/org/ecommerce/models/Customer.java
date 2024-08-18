package org.ecommerce.models;

public class Customer extends User {
    public Customer(Long id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
    }
}
