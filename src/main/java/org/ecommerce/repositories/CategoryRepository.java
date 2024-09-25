package org.ecommerce.repositories;

import org.ecommerce.models.Category;
import org.ecommerce.repositories.impl.CrudOperationsImpl;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository extends CrudOperationsImpl<Category> {
}
