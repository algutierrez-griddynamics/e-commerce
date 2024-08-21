package org.ecommerce.repositories;

import org.ecommerce.models.Category;
import org.ecommerce.util.CrudOperations;

import java.util.List;

public class CategoryRepository implements CrudOperations<Category> {
    @Override
    public Category save(Category entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Category findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Category> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
