package org.ecommerce.enums;

    public enum Error {
        ENTITY_NOT_FOUND("There is not register stored of such id"),
        INVALID_NAME("Provided entity name is invalid (at least 2 characters required)."),
        INVALID_EMAIL("Email address provided is invalid."),
        PASSWORD_MISMATCH("Old and current passwords do not match."),
        PASSWORD_FORMAT("The password provided has a wrong format (at least 1 lower & upper case letter, 1 digit and 1 special symbol)."),
        INVALID_REQUEST_FORMAT("The request format provided is not valid"),
        INSERT_EXCEPTION("An error happen when inserting data into the database."),
        SELECT_EXCEPTION("An error happen when getting data from the database"),
        UPDATE_EXCEPTION("An error happen when updating data from the database"),
        DELETE_EXCEPTION("An error happen when deleting data from the database"),
        MAPPING_EXCEPTION("An error happen when mapping data from the database");

        private final String description;

        Error(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
