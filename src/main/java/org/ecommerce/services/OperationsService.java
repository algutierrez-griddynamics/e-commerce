package org.ecommerce.services;

import java.util.List;

public interface OperationsService<T, ID> {
    T create(T entity);
    T update(ID id, T entity);
    void delete(ID id);
    List<T> findAll();
    T findById(ID id);
}
