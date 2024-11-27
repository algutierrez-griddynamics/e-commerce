package org.ecommerce.payment_details_service.models;

public class Identity {
    private Long id;

    Identity(Long id) {
        this.id = id;
    }

    Identity(){}

    public Long getId() {
        return id;
    }
}
