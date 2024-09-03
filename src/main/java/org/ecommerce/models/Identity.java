package org.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class Identity {
    private Long id;

    @Override
    public String toString() {
        return "id=" + id;
    }
}
