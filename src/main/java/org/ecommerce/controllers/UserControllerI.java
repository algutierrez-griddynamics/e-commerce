package org.ecommerce.controllers;

import org.ecommerce.models.Response;
import org.ecommerce.models.User;

import java.util.Map;

public interface UserControllerI <T> {
   Response<? super User> createUser(Map<String, String> request);
   Response<?> deleteUser();
   Response<? super User> updateUser(Map<String, String> request);
   Response<? super User> getUser();
}
