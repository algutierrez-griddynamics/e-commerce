package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@ToString(callSuper = true)
public class Customer extends User {
    private String phoneNumber;
    private String address;
    private List<String> preferences;
}
