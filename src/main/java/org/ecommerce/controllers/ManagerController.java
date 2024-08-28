package org.ecommerce.controllers;

import org.ecommerce.models.Manager;
import org.ecommerce.models.Response;
import org.ecommerce.models.User;
import org.ecommerce.services.impl.ManagerServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class ManagerController extends AbstractUserController implements UserControllerI<Manager> {

    private final ManagerServiceImpl managerService;

    public ManagerController(ManagerServiceImpl managerService) {
        this.managerService = managerService;
    }

    @Override
    public Response<? super User> createUser(Map<String, String> request) {
        Response<Manager> response = new Response<>();
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

    public Response<User> listUsers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Response<User> getUserById(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
