package org.ecommerce.repositories;

import org.ecommerce.models.User;
import org.ecommerce.services.impl.CrudOperationsImpl;

import java.util.*;

public class UserRepository extends CrudOperationsImpl<User> {

    public String findPasswordById(Long id) {
        return Optional.ofNullable(getDb().get(id))
                .map(User::getPassword)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    public User updatePasswordById(Long id, String newPassword) {
        User currentUser = getDb().get(id);
        currentUser.setPassword(newPassword);
        return Optional.ofNullable(getDb().get(id))
                .map(user -> {
                    user.setPassword(newPassword);
                    return getDb().get(id);
                })
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));}
}
