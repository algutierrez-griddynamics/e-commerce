package org.ecommerce.services.impl;

import org.ecommerce.models.User;
import org.ecommerce.repositories.inmemory.UserRepository;
import org.ecommerce.services.PasswordService;
import org.ecommerce.services.UserService;

import java.util.List;

public class CustomerServiceImpl implements UserService<User> {

    private final UserRepository userRepository;
    private final PasswordService passwordService;

    public CustomerServiceImpl(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    @Override
    public User create(User entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User update(User entity, Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User findById(Long aLong) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean isValidEntity(User obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
