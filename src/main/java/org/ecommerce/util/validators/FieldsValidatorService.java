package org.ecommerce.util.validators;

public interface FieldsValidatorService<T> {
    boolean isValidInput(T obj);
}
