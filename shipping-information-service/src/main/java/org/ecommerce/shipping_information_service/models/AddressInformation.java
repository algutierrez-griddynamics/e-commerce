package org.ecommerce.shipping_information_service.models;

public class AddressInformation extends Identity {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    public AddressInformation() {}

    public AddressInformation(Long id) {
        super(id);
    }

    public AddressInformation(Long id, String street, String city, String state, String zipCode, String country) {
        super(id);
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }
}
