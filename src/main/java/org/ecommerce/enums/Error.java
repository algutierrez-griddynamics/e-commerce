package org.ecommerce.enums;

    public enum Error {
        INVALID_EMAIL("Email address provided is invalid."),
        PASSWORD_MISMATCH("Old and current passwords do not match."),
        PASSWORD_FORMAT("The password provided has a wrong format (at least 1 lower & upper case letter, 1 digit and 1 special symbol).");

        private final String description;

        Error(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
