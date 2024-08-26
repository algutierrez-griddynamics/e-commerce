package org.ecommerce.services;

import java.util.Map;
import java.util.Optional;

public interface PasswordService extends BusinessValidatorService<String> {
    Map<String, String> changePassword(Long id, Map<String, String> passwords);
}
