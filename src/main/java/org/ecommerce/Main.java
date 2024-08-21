package org.ecommerce;

import org.ecommerce.controllers.UserController;
import org.ecommerce.repositories.UserRepository;
import org.ecommerce.services.EmailService;
import org.ecommerce.services.PasswordService;
import org.ecommerce.services.impl.UserServiceImpl;
import org.ecommerce.services.impl.EmailServiceImpl;
import org.ecommerce.services.impl.PasswordServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final Map<String, String> request = new HashMap<>();
    private static final UserRepository customerRepository = new UserRepository();
    private static final PasswordService passwordService = new PasswordServiceImpl(customerRepository);
    private static final EmailService emailService = new EmailServiceImpl();
    private static final UserServiceImpl customerService = new UserServiceImpl(customerRepository, passwordService, emailService);

    private static final UserController customerController = new UserController(customerService, passwordService);

    public static void main(String[] args) {
        request.put("id", "1");
        request.put("oldPassword", "Mario123!");
        request.put("newPassword", "1357924680mM!");

        var response = customerController.processChangePasswordForm(request);

        if(response.containsKey("error")) System.out.println(response.get("error"));
        else System.out.println(response.get("updatedUser"));
    }
}
