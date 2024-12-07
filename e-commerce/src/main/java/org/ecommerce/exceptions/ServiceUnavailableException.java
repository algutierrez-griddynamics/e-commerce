package org.ecommerce.exceptions;

public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException(String message) {
        super(message);
    }
    public ServiceUnavailableException(Throwable cause) {
        super(cause);
    }
}
