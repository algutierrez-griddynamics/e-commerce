package org.ecommerce.controllers;

import org.ecommerce.models.Customer;
import org.ecommerce.models.Response;
import org.ecommerce.models.User;

import java.util.List;
import java.util.Map;

public class CustomerController extends AbstractUserController implements UserControllerI<Customer>{
    @Override
    public Response<Customer> createUser(Map<String, String> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Customer> deleteUser(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Customer> updateUser(Long id, Map<String, String> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Customer> getUser(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<List<Customer>> getAllUsers() {
        return null;
    }

}
