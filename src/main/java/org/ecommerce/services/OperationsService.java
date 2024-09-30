package org.ecommerce.services;

import java.util.List;

public interface OperationsService<T, ID> {
    T create(T entity);
    T update(T entity, ID id);
    void delete(ID id);
    List<T> findAll();
    T findById(ID id);
}
