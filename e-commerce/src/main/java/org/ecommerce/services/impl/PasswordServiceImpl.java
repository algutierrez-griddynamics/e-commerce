package org.ecommerce.services.impl;

import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.exceptions.InvalidInput;
import org.ecommerce.repositories.inmemory.UserRepository;
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
        String oldPassword = passwords.get("oldPassword");
        String newPassword = passwords.get("newPassword");

        String currentPassword = userRepository.findPasswordById(id)
                .orElseThrow(() -> new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription()));


        if(!Validators.stringsMatch(currentPassword, oldPassword)) {
            throw new InvalidInput(Error.PASSWORD_MISMATCH.getDescription());
        }
        if(!Validators.isValidPassword(newPassword)) {
            throw new InvalidInput(Error.PASSWORD_FORMAT.getDescription());
        }

        result.put("updatedUser",
                String.valueOf(userRepository.updatePasswordById(id, newPassword)
                        .orElseThrow(() -> new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription()))));
        return result;
    }

}
