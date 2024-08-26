package org.ecommerce.services.impl;

import org.ecommerce.enums.Error;
import org.ecommerce.repositories.UserRepository;
import org.ecommerce.services.PasswordService;
import org.ecommerce.util.validators.impl.Validators;

import java.util.HashMap;
import java.util.Map;

public class PasswordServiceImpl implements PasswordService {

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
        if(!isValidInput(newPassword))
            result.put("error", Error.PASSWORD_FORMAT.getDescription());
        if(result.isEmpty())
            result.put("updatedUser", String.valueOf(userRepository.updatePasswordById(id, newPassword)));
        return result;
    }

    @Override
    public boolean isValidInput(String password) {
        return Validators.isValidPassword(password);
    }
}
