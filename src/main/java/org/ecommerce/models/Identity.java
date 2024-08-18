package org.ecommerce.models;

public abstract class Identity {
    private final Long id;

    Identity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
