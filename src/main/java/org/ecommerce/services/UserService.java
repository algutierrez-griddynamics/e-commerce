package org.ecommerce.services;

import org.ecommerce.models.User;

public interface UserService<T extends User> extends CrudOperations<T, Long> {

}
