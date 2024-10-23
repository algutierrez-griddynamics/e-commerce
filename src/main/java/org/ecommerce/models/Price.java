package org.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "prices")
@AttributeOverride(name="id", column=@Column(name="pk_price_id"))
public class Price extends Identity implements Serializable {
    @NotNull @Column(nullable = false)
    private Currency currencyCode;
    @NotNull @Column(nullable = false)
    private BigDecimal amount;

    @JsonCreator
    public Price(@JsonProperty("currencyCode") Currency currencyCode,
                 @JsonProperty("amount") BigDecimal amount) {
        this.currencyCode = currencyCode;
        this.amount = amount;
    }
}
