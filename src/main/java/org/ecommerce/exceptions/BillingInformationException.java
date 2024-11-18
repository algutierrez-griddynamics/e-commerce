package org.ecommerce.exceptions;

public class BillingInformationException extends RuntimeException {
    public BillingInformationException(String message) {
        super(message);
    }
    public BillingInformationException(String message, Throwable cause) {
        super(message, cause);
    }
}
