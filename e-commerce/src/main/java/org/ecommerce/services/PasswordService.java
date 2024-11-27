package org.ecommerce.services;

import java.util.Map;

public interface PasswordService {
    Map<String, String> changePassword(Long id, Map<String, String> passwords);
}
