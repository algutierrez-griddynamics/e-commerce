package org.ecommerce.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
public abstract class User extends Identity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
