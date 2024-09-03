package org.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
public class Price {
    private Currency currencyCode;
    private BigDecimal amount;

    @JsonCreator
    public Price(@JsonProperty("currencyCode") Currency currencyCode,
                 @JsonProperty("amount") BigDecimal amount) {
        this.currencyCode = currencyCode;
        this.amount = amount;
    }
}
