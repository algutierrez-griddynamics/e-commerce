package org.ecommerce.services;


import org.ecommerce.util.validators.fieldsValidatorService;

import java.util.Map;

public interface PasswordService extends fieldsValidatorService<String> {
    Map<String, String> changePassword(Long id, Map<String, String> passwords);
    boolean isValidInput(String password);
}
