package org.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "prices")
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
