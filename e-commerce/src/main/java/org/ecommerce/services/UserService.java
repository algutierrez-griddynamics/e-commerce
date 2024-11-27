package org.ecommerce.services;

import org.ecommerce.models.User;
import org.ecommerce.util.validators.FieldsValidatorService;

public interface UserService<T extends User> extends OperationsService<T, Long>, FieldsValidatorService<T> {

}
