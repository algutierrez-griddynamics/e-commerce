package org.ecommerce.exceptions;

public class ShippingInformationException extends RuntimeException {
    public ShippingInformationException(String message) {
        super(message);
    }
    public ShippingInformationException(String message, Throwable cause) {
        super(message, cause);
    }
}
