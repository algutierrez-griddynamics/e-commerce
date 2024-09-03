package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
public class Customer extends User {
    private String phoneNumber;
    private String address;
    private List<String> preferences;

    @Override
    public String toString() {
        return "Customer{" +
                super.toString() +
                "phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", preferences=" + preferences +
                '}';
    }
}
