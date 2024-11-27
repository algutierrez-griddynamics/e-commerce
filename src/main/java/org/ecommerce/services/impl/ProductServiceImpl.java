package org.ecommerce.services.impl;

import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.models.Product;
import org.ecommerce.repositories.inmemory.ProductRepository;
import org.ecommerce.repositories.jpa.ProductJpaRepository;
import org.ecommerce.services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductJpaRepository productJpaRepository;

    public ProductServiceImpl(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Product create(Product entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Product update(Product entity, Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Product> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Product findById(Long id) {
        return productJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription()));
    }
}
