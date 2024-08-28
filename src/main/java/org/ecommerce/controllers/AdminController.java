package org.ecommerce.controllers;

import org.ecommerce.models.Admin;
import org.ecommerce.models.Response;
import org.ecommerce.models.User;

import java.util.List;
import java.util.Map;

public class AdminController implements UserControllerI<Admin> {
    @Override
    public Response<Admin> createUser(Map<String, String> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Admin> deleteUser(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Admin> updateUser(Long id, Map<String, String> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Admin> getUser(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<List<Admin>> getAllUsers() {
        return null;
    }

}
