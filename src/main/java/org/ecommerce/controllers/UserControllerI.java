package org.ecommerce.controllers;

import org.ecommerce.models.Response;
import org.ecommerce.models.User;

import java.util.List;
import java.util.Map;

public interface UserControllerI <T extends User> {
   Response<T> createUser(Map<String, String> request);
   Response<T> deleteUser(Long id);
   Response<T> updateUser(Long id, Map<String, String> request);
   Response<T> getUser(Long id);
   Response<List<T>> getAllUsers();
}
