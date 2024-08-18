package org.ecommerce.services;

import org.ecommerce.models.Customer;
import org.ecommerce.models.User;
import org.ecommerce.repositories.CustomerRepository;

import java.util.HashMap;
import java.util.Map;

public class PasswordServiceImpl implements PasswordService {
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$";

    private final CustomerRepository customerRepository;


    public PasswordServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Map<String, String> changePassword(Long id, Map<String, String> passwords) {
        Map<String, String> result = new HashMap<>();
        String currentPassword = customerRepository.findPasswordById(id);
        String oldPassword = passwords.get("oldPassword");
        String newPassword = passwords.get("newPassword");
        if(currentPassword.equals(oldPassword)) {
            if(matchesBusinessRules(newPassword)) result.put("updatedUser", String.valueOf(customerRepository.updatePasswordById(id, newPassword)));
            else result.put("error", "New password provided has a wrong format (at least 1 lower & upper case letter, 1 digit and 1 special symbol).");
        } else result.put("error", "Old and current passwords do not match.");
        return result;
    }

    private boolean matchesBusinessRules(String newPassword) {
        return newPassword.matches(PASSWORD_REGEX);
    }

}
