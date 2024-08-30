package org.ecommerce.controllers;

import org.ecommerce.models.Customer;
import org.ecommerce.models.Response;

import java.util.List;
import java.util.Map;

public class CustomerController extends AbstractUserController implements ControllerOperations<Customer, Long> {
    @Override
    public Response<Customer> create(Map<String, String> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Customer> delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Customer> update(Long id, Map<String, String> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Customer> get(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<List<Customer>> get() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
