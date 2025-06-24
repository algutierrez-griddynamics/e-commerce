package org.ecommerce.exceptions;

public class PaymentDetailsException extends RuntimeException {
    public PaymentDetailsException(String message) {
        super(message);
    }

    public PaymentDetailsException(String message, Throwable cause) {
        super(message, cause);
    }

}
