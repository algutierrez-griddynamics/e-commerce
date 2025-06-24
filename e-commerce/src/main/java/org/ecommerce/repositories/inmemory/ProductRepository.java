package org.ecommerce.repositories.inmemory;

import org.ecommerce.models.Product;
import org.ecommerce.repositories.inmemory.impl.CrudInMemoryOperationsImpl;
import org.ecommerce.util.database.Operations;

public class ProductRepository extends CrudInMemoryOperationsImpl<Product> {
    public ProductRepository(Operations<Product> operations) {
        super(operations);
    }
}
