package org.ecommerce.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@ToString(callSuper = true)
@NoArgsConstructor
public class Customer extends User {
    private String phoneNumber;
    private String address;
    private List<String> preferences;
}
