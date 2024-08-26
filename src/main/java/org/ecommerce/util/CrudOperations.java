package org.ecommerce.util;

import java.util.List;

public interface CrudOperations<T, ID> {
    T save(T entity);
    T findById(ID id);
    void deleteById(ID id);
    List<T> findAll();
}
