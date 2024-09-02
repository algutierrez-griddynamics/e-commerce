package org.ecommerce.models;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class Identity {
    private final Long id;

    Identity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
