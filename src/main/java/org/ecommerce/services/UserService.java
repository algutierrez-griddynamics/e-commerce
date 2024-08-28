package org.ecommerce.services;

import org.ecommerce.models.User;

public interface UserService<T extends User> extends OperationsService<T, Long> {

}
