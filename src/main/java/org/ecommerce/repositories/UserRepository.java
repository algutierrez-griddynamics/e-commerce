package org.ecommerce.repositories;

import org.ecommerce.models.User;
import org.ecommerce.util.CrudOperations;

import java.util.List;

public class UserRepository implements CrudOperations<User> {


    @Override
    public User save(User entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    public String findPasswordById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public User updatePasswordById(Long id, String newPassword) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
