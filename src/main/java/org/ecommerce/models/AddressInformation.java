package org.ecommerce.models;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public class AddressInformation extends Identity {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
