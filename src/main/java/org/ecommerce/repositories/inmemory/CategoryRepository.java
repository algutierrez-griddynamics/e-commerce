package org.ecommerce.repositories.inmemory;

import org.ecommerce.models.Category;
import org.ecommerce.repositories.inmemory.impl.CrudInMemoryOperationsImpl;
import org.ecommerce.util.database.Operations;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository extends CrudInMemoryOperationsImpl<Category> {
    protected CategoryRepository(Operations<Category> operations) {
        super(operations);
    }
}
