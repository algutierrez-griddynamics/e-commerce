package org.ecommerce.dtos.responses;

import org.ecommerce.models.Price;

public record ProductDTO (
        String name,
        Price price,
        String description
) {
}
