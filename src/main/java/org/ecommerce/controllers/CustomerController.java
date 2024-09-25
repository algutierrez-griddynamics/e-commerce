package org.ecommerce.controllers;

import org.ecommerce.models.Customer;
import org.ecommerce.models.Response;

import java.util.List;
import java.util.Optional;

public class CustomerController extends AbstractUserController implements ControllerOperations<Customer, Long> {
    @Override
    public Response<Customer> create(String jsonRequest) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Customer> delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Customer> update(String request, Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Customer> get(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<List<Customer>> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Customer> parseJson(String json) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
