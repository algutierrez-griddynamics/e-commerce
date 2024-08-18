package org.ecommerce;

import org.ecommerce.controllers.CustomerController;
import org.ecommerce.controllers.UserController;
import org.ecommerce.repositories.CustomerRepository;
import org.ecommerce.services.CustomerService;
import org.ecommerce.services.PasswordServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final Map<String, Object> REQUEST = new HashMap<>();
    private static final CustomerRepository customerRepository = new CustomerRepository();
    private static final UserController customerController = new CustomerController(new CustomerService(customerRepository), new PasswordServiceImpl(customerRepository));
    public static void main(String[] args) {
        REQUEST.put("id", 1L);
        REQUEST.put("passwords", Map.of("oldPassword", "Mario123!", "newPassword", "1357924680mM!"));
        var response = customerController.changePassword(REQUEST);

        if(response.containsKey("error")) System.out.println(response.get("error"));
        else System.out.println(response.get("updatedUser"));
    }
}
