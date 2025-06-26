package org.ecommerce.billing_information_service.models;

public abstract class Identity {
    private Long id;

    public Identity() {
    }

    public Identity(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
