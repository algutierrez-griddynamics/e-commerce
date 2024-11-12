package org.ecommerce.validations;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@JsonSerialize
@Getter
public class Violation {
    private String field;
    private String message;

    public Violation(String field, String message) {
        this.field = field;
        this.message = message;
    }
    public Violation (String message) {
        this.field = "Unresolved";
        this.message = message;
    }
}
