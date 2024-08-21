package org.ecommerce.services.impl;

import org.ecommerce.repositories.UserRepository;
import org.ecommerce.services.PasswordService;
import org.ecommerce.util.Error;

import java.util.HashMap;
import java.util.Map;

public class PasswordServiceImpl implements PasswordService {
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$";

    private final UserRepository userRepository;

    public PasswordServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Map<String, String> changePassword(Long id, Map<String, String> passwords) {
        Map<String, String> result = new HashMap<>();
        String currentPassword = userRepository.findPasswordById(id);
        String oldPassword = passwords.get("oldPassword");
        String newPassword = passwords.get("newPassword");
        if(!currentPassword.equals(oldPassword))
            result.put("error", Error.PASSWORD_MISMATCH.getDescription());
        if(!meetsBusinessRules(newPassword))
            result.put("error", Error.PASSWORD_FORMAT.getDescription());
        if(result.isEmpty())
            result.put("updatedUser", String.valueOf(userRepository.updatePasswordById(id, newPassword)));
        return result;
    }

    // This method returns true if the provided password has at least 1 lower & upper case letter,
    // 1 digit, 1 special symbol and its length is between 8 and 16 characters inclusive.
    @Override
    public boolean meetsBusinessRules(String newPassword) {
        return newPassword.matches(PASSWORD_REGEX);
    }

}
