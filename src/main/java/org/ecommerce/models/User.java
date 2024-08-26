package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class User extends Identity {
    private String firstName;
    private String LastName;
    private String email;

    private String password;

    public User(Long id, String firstName, String lastName, String email, String password) {
        super(id);
        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString() +
                "firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
