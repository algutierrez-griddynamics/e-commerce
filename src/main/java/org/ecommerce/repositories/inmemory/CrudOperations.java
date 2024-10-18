package org.ecommerce.repositories.inmemory;

import java.util.List;

import java.util.Optional;

public interface CrudOperations<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    void update(ID id, T entity);
    void deleteById(ID id);
    List<T> findAll();
}
