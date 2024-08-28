package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Customer extends User {
    private String phoneNumber;
    private String address;
    private List<String> preferences;

    public Customer(Long id, String firstName, String lastName, String email, String password, String phoneNumber, String address, List<String> preferences) {
        super(id, firstName, lastName, email, password);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.preferences = preferences;
    }

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
