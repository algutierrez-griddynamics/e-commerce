package org.ecommerce.validations;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@JsonSerialize
@Getter
public class Violation {
    private String cause;
    private String message;

    public Violation(String cause, String message) {
        this.cause = cause;
        this.message = message;
    }
    public Violation (String message) {
        this.cause = "Unresolved";
        this.message = message;
    }
}
