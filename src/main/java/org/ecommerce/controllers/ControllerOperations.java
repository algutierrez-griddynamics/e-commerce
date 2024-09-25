package org.ecommerce.controllers;

import org.ecommerce.models.Response;

import java.util.List;
import java.util.Optional;

public interface ControllerOperations<T, ID> {
   Response<T> create(String jsonRequest);
   Response<T> delete(ID id);
   Response<T> update(String request, ID id);
   Response<T> get(ID id);
   Response<List<T>> getAll();
   Optional<T> parseJson(String json);
}
