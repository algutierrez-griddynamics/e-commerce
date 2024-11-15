package org.ecommerce.services.jpa.impl;

import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.models.Product;
import org.ecommerce.repositories.jpa.ProductJpaRepository;
import org.ecommerce.services.OperationsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductJpaServiceImpl implements OperationsService <Product, Long> {

    private final ProductJpaRepository productJpaRepository;

    public ProductJpaServiceImpl(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Product create(Product entity) {
        throw new UnsupportedOperationException("Not Supported yet");
    }

    @Override
    public Product update(Product entity, Long aLong) {
        throw new UnsupportedOperationException("Not Supported yet");
    }

    @Override
    public void delete(Long aLong) {
        throw new UnsupportedOperationException("Not Supported yet");
    }

    @Override
    public List<Product> findAll() {
        throw new UnsupportedOperationException("Not Supported yet");
    }

    @Override
    public Product findById(Long productId) {
        return productJpaRepository.findById(productId).orElseThrow(
                () -> new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription())
        );
    }
}
