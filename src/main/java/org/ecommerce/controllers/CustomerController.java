package org.ecommerce.controllers;

import org.ecommerce.models.Customer;
import org.ecommerce.models.Response;
import org.ecommerce.models.User;

import java.util.Map;

public class CustomerController implements UserControllerI<Customer>{
    @Override
    public Response<? super User> createUser(Map<String, String> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<?> deleteUser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<? super User> updateUser(Map<String, String> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<? super User> getUser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
