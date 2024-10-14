package org.ecommerce.repositories.inmemory.impl;

import lombok.Getter;
import org.ecommerce.enums.Error;
import org.ecommerce.models.Identity;
import org.ecommerce.repositories.inmemory.CrudOperations;
import org.ecommerce.util.database.Operations;

import java.util.*;

@Getter
public abstract class CrudInMemoryOperationsImpl<T extends Identity> implements CrudOperations<T, Long> {

    static Long id = 0L;
    Map<Long, T> db = new HashMap<>();
    protected final Operations<T> operations;

    protected CrudInMemoryOperationsImpl(Operations<T> operations) {
        this.operations = operations;
    }

    @Override
    public T save(T entity) {
        Long id = generateId();
        entity.setId(id);
        db.put(id, entity);
        return db.get(id);
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public void update(Long id, T entity) {
        db.put(id, entity);
    }

    @Override
    public void deleteById(Long id) {
        findById(id)
                .ifPresentOrElse((user) -> db.remove(id),
                        () -> {throw new NoSuchElementException(Error.ENTITY_NOT_FOUND.getDescription());
                        });
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(db.values());
    }

    private Long generateId() {
        return id++;
    }

}
