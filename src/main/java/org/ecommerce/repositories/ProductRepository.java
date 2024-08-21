package org.ecommerce.repositories;

import org.ecommerce.models.Product;
import org.ecommerce.util.CrudOperations;

import java.util.List;

public class ProductRepository implements CrudOperations<Product> {


    @Override
    public Product save(Product entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Product findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Product> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
