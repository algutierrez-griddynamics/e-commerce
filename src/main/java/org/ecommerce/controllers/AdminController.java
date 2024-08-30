package org.ecommerce.controllers;

import org.ecommerce.models.Admin;
import org.ecommerce.models.Response;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AdminController implements ControllerOperations<Admin, Long> {
    @Override
    public Response<Admin> create(Map<String, String> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Admin> delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Admin> update(Long id, Map<String, String> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Admin> get(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<List<Admin>> get() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Admin> parseJson(String json) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
