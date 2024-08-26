package org.ecommerce.models;

public class Admin extends User {
    public Admin(Long id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
    }
}
