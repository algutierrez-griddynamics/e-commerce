package org.ecommerce.api_gateway.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Jwks {

    @NotNull
    private final String kty;

    @NotNull
    private final String e;

    @NotNull
    private final String kid;

    @NotNull
    private final String n;

    @JsonCreator
    public Jwks(
            @JsonProperty("kty") String kty,
            @JsonProperty("e") String e,
            @JsonProperty("kid") String kid,
            @JsonProperty("n") String n) {
        this.kty = kty;
        this.e = e;
        this.kid = kid;
        this.n = n;
    }
}
