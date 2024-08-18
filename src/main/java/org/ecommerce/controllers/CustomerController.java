package org.ecommerce.controllers;

import org.ecommerce.models.Customer;
import org.ecommerce.services.CustomerService;
import org.ecommerce.services.PasswordService;

import java.util.Map;

public class CustomerController implements UserController {
    private final CustomerService customerService;

    private final PasswordService passwordService;

    public CustomerController(CustomerService customerService, PasswordService passwordService) {
        this.customerService = customerService;
        this.passwordService = passwordService;
    }

    @Override
    public Map<String, String> changePassword(Map<String, ?> request) {
        return passwordService.changePassword((Long) request.get("id"), (Map<String, String>) request.get("passwords"));
    }
}
