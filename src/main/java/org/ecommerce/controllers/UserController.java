package org.ecommerce.controllers;

import org.ecommerce.util.CrudOperations;

import java.util.Map;

public interface UserController{
    Map<String, String> changePassword(Map<String, ?> request);
}
