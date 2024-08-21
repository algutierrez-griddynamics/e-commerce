package org.ecommerce.services;

public interface BusinessValidatorService<T> {
    boolean meetsBusinessRules(T obj);
}
