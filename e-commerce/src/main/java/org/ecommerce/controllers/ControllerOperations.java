package org.ecommerce.controllers;

import org.ecommerce.models.Response;
import org.ecommerce.models.requests.*;

import java.util.List;

public interface ControllerOperations<T, ID> {
   Response<T> create(CreateRequest<T> request);
   Response<T> delete(DeleteRequest<ID> request);
   Response<T> update(UpdateRequest<T, ID> request);
   Response<T> get(GetRequest<ID> request);
   Response<List<T>> getAll(GetAllRequest request);
}
