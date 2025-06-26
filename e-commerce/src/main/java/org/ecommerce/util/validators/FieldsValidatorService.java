package org.ecommerce.util.validators;

public interface FieldsValidatorService<T> {
    Boolean isValidEntity(T obj);
}
