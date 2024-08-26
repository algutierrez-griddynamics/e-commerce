package org.ecommerce.services;

import org.ecommerce.models.User;
import org.ecommerce.util.CrudOperations;

public interface UserService<T extends User> extends CrudOperations<T, Long>{

}
