package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInformation extends Identity {

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    AddressInformation() {}

    AddressInformation(Long id) {
        super(id);
    }
}
