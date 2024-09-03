package org.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@Setter
@Getter
public abstract class User extends Identity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Override
    public String toString() {
        return super.toString() +
                "firstName='" + firstName + '\'' +
                ", LastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
