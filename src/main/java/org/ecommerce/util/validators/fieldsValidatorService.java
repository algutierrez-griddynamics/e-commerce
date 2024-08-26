package org.ecommerce.util.validators;

public interface fieldsValidatorService<T> {
    boolean isValidInput(T obj);
}
