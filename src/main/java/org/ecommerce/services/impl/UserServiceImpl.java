package org.ecommerce.services.impl;

import org.ecommerce.enums.Error;
import org.ecommerce.models.Customer;
import org.ecommerce.models.User;
import org.ecommerce.repositories.UserRepository;
import org.ecommerce.services.EmailService;
import org.ecommerce.services.PasswordService;
import org.ecommerce.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService<User> {

    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, PasswordService passwordService, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.emailService = emailService;
    }


    @Override
    public User save(User entity) {
       return userRepository.save(entity);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Map<String, String> generateCustomer(Customer customer) {
        Map<String, String> result = new HashMap<>();
        if (!emailService.isValidInput(customer.getEmail()))
            result.put("error", Error.INVALID_EMAIL.getDescription());
        if (!passwordService.isValidInput(customer.getPassword()))
            result.put("error", Error.PASSWORD_FORMAT.getDescription());
        if (result.isEmpty())
            result.put("generatedCustomer", String.valueOf(save(customer)));
        return result;
    }
}
