package org.ecommerce.repositories.inmemory;

import org.ecommerce.models.User;
import org.ecommerce.repositories.inmemory.impl.CrudInMemoryOperationsImpl;
import org.ecommerce.util.database.Operations;

import java.util.*;

public class UserRepository extends CrudInMemoryOperationsImpl<User> {

    public UserRepository(Operations<User> operations) {
        super(operations);
    }

    public Optional<String> findPasswordById(Long id) {
        return Optional.ofNullable(getDb().get(id))
                .map(User::getPassword);
    }

    public Optional<User> updatePasswordById(Long id, String newPassword) {
        User currentUser = getDb().get(id);
        currentUser.setPassword(newPassword);
        return Optional.ofNullable(getDb().get(id))
                .map(user -> {
                    user.setPassword(newPassword);
                    return getDb().get(id);
                });
    }
}
