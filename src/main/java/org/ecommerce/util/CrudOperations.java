package org.ecommerce.util;

import java.util.List;

public interface CrudOperations<T> {
    T create(T t);
    T getById(Long id);
    T update(Long id, T t);
    void deleteById(Long id);

    List<T> findAll();
}
