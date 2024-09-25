package org.ecommerce.controllers;

import org.ecommerce.models.Admin;
import org.ecommerce.models.Response;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController implements ControllerOperations<Admin, Long> {
    @Override
    public Response<Admin> create(String jsonRequest) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Admin> delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Admin> update(String request, Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Admin> get(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<List<Admin>> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Admin> parseJson(String json) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
