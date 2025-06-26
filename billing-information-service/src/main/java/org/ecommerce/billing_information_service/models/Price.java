package org.ecommerce.billing_information_service.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

public class Price extends Identity implements Serializable {
    private Currency currencyCode;
    private BigDecimal amount;

    @JsonCreator
    public Price(@JsonProperty("currencyCode") Currency currencyCode,
                 @JsonProperty("amount") BigDecimal amount) {
        this.currencyCode = currencyCode;
        this.amount = amount;
    }
}