package org.ecommerce.controllers;

import org.ecommerce.models.Admin;
import org.ecommerce.models.Response;
import org.ecommerce.models.requests.*;

import java.util.List;

public class AdminController implements ControllerOperations<Admin, Long> {
    @Override
    public Response<Admin> create(CreateRequest<Admin> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Admin> delete(DeleteRequest<Long> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Admin> update(UpdateRequest<Admin, Long> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<Admin> get(GetRequest<Long> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response<List<Admin>> getAll(GetAllRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
