package org.ecommerce.util;

import java.util.List;

public interface CrudOperations<T> {
    T save(T entity);
    T findById(Long id);
    void deleteById(Long id);
    List<T> findAll();
}
