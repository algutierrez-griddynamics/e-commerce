package org.ecommerce.util.validators.impl;

public class Validators {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$";

    // validates emails by matching:
    // - Local part: alphanumeric + special chars (_+&*-)
    // - Domain: alphanumeric + hyphens, allowing subdomains
    // - TLD: 2-7 alphabetic chars
    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    // This method returns true if the provided password has at least 1 lower & upper case letter,
    // 1 digit, 1 special symbol and its length is between 8 and 16 characters inclusive.
    public static boolean isValidPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }



}