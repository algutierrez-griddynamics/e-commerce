package org.ecommerce.mappers;

import org.ecommerce.dtos.responses.ProductDTO;
import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.MappingException;
import org.ecommerce.models.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class ProductsDTOMapper implements Function<Product, ProductDTO> {

    @Override
    public ProductDTO apply(Product product) {
        return Optional.ofNullable(product)
                .map(p -> new ProductDTO(
                        p.getName(),
                        p.getPrice(),
                        p.getDescription()
                ))
                .orElseThrow(
                        () -> new MappingException(getClass().getSimpleName(), Error.MAPPING_EXCEPTION.getDescription())
                );
    }
}
