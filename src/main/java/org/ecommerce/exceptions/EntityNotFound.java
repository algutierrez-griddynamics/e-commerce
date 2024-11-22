package org.ecommerce.exceptions;

import lombok.Getter;

@Getter
public class EntityNotFound extends RuntimeException {
    private String entity;

    public EntityNotFound(String entity, String message) {
        super(message);
        this.entity = entity;
    }

    public EntityNotFound(String message) {
        super(message);
    }

    public EntityNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
