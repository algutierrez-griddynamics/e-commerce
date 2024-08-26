package org.ecommerce.repositories;

import org.ecommerce.models.User;
import org.ecommerce.services.impl.CrudOperationsImpl;

import java.util.*;

public class UserRepository extends CrudOperationsImpl<User> {

    public String findPasswordById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public User updatePasswordById(Long id, String newPassword) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
