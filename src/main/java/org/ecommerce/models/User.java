package org.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
public abstract class User extends Identity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
