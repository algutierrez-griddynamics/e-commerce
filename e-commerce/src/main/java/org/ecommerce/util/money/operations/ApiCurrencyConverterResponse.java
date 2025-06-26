package org.ecommerce.util.money.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

@Getter
@JsonIgnoreProperties
public class ApiCurrencyConverterResponse implements Serializable {
    @NotNull
    private Boolean success;

    @NotNull
    private Integer timestamp;

    @NotNull
    private String base;

    @NotNull
    private String date;

    @NotNull
    private Map<String, Double> rates;

    @JsonCreator
    public ApiCurrencyConverterResponse(
            @JsonProperty("success") Boolean success,
            @JsonProperty("timestamp") Integer timestamp,
            @JsonProperty("base") String base,
            @JsonProperty("date") String date,
            @JsonProperty("rates") Map<String, Double> rates) {
        this.success = success;
        this.timestamp = timestamp;
        this.base = base;
        this.date = date;
        this.rates = rates;
    }
}
