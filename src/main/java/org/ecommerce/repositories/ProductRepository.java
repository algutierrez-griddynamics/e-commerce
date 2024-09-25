package org.ecommerce.repositories;

import org.ecommerce.models.Product;
import org.ecommerce.repositories.impl.CrudOperationsImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends CrudOperationsImpl<Product> {
}
