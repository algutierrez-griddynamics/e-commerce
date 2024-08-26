package org.ecommerce.models;

import java.util.List;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
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
