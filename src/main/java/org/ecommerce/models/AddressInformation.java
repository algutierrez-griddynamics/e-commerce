package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInformation extends Identity {

    AddressInformation(Long id) {
        super(id);
    }

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
