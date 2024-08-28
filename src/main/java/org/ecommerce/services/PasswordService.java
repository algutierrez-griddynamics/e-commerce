package org.ecommerce.services;


import org.ecommerce.util.validators.FieldsValidatorService;

import java.util.Map;

public interface PasswordService {
    Map<String, String> changePassword(Long id, Map<String, String> passwords);
}
