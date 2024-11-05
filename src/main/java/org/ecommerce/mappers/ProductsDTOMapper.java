package org.ecommerce.mappers;

import org.ecommerce.dtos.responses.ProductDTO;
import org.ecommerce.models.Product;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductsDTOMapper implements Function<Product, ProductDTO> {

    @Override
    public ProductDTO apply(Product product) {
        return null;
    }
}
