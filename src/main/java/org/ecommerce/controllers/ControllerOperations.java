package org.ecommerce.controllers;

import org.ecommerce.models.Response;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ControllerOperations<T, ID> {
   Response<T> create(Map<String, String> request);
   Response<T> delete(ID id);
   Response<T> update(ID id, Map<String, String> request);
   Response<T> get(ID id);
   Response<List<T>> get();
   Optional<T> parseJson(String json);
}
