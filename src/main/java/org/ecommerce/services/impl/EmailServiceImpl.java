package org.ecommerce.services.impl;

import org.ecommerce.services.EmailService;

public class EmailServiceImpl implements EmailService {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    // validates emails by matching:
    // - Local part: alphanumeric + special chars (_+&*-)
    // - Domain: alphanumeric + hyphens, allowing subdomains
    // - TLD: 2-7 alphabetic chars
    @Override
    public boolean meetsBusinessRules(String email) {
        return email.matches(EMAIL_REGEX);
    }
}
