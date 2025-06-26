package org.ecommerce.controllers;

import org.ecommerce.models.Customer;
import org.ecommerce.models.Response;
import org.ecommerce.models.requests.*;
import org.ecommerce.services.PasswordService;

import java.util.List;

public class CustomerController extends AbstractUserController implements ControllerOperations<Customer, Long> {
    public CustomerController(PasswordService passwordService) {
        super(passwordService);
    }

    @Override
    public Response<Customer> create(CreateRequest<Customer> request ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Customer> delete(DeleteRequest<Long> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Customer> update(UpdateRequest<Customer, Long> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Customer> get(GetRequest<Long> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<List<Customer>> getAll(GetAllRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
