package org.ecommerce.services;


import org.ecommerce.util.validators.FieldsValidatorService;

import java.util.Map;

public interface PasswordService extends FieldsValidatorService<String> {
    Map<String, String> changePassword(Long id, Map<String, String> passwords);
    boolean isValidInput(String password);
}
