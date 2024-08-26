package org.ecommerce.controllers;

import org.ecommerce.models.Customer;
import org.ecommerce.services.impl.UserServiceImpl;
import org.ecommerce.services.PasswordService;

import java.util.Collections;
import java.util.Map;

public class UserController {
    private final UserServiceImpl customerService;
    private final PasswordService passwordService;

    public UserController(UserServiceImpl customerService, PasswordService passwordService) {
        this.customerService = customerService;
        this.passwordService = passwordService;
    }

    public Map<String, String> processCreationForm(Map<String, String> request) {
        Customer customer = new Customer(
                Long.valueOf(request.get("id")),
                request.get("firstName"),
                request.get("lastName"),
                request.get("email"),
                request.get("password"),
                request.get("phoneNumber"),
                request.get("address"),
                Collections.singletonList(request.get("preferences"))
        );
        return customerService.generateCustomer(customer);
    }

    public Map<String, String> processChangePasswordForm(Map<String, String> request) {
        return passwordService.changePassword(
                Long.valueOf(request.get("id")),
                Map.of("oldPassword", request.get("oldPassword"),
                        "newPassword", request.get("newPassword"))
        );
    }
}
