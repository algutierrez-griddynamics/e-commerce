package org.ecommerce.exceptions;

import lombok.Getter;

@Getter
public class MappingException extends RuntimeException {
    private String mapperClass;

    public MappingException(String mapperClass, String message) {
        super(message);
        this.mapperClass = mapperClass;
    }
    public MappingException(String message) {
        super(message);
    }
    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }
}
