package org.ecommerce.controllers;

import org.ecommerce.models.*;
import org.ecommerce.services.impl.ManagerServiceImpl;

import java.util.List;
import java.util.Map;

public class ManagerController extends AbstractUserController implements UserControllerI<Manager> {

    private final ManagerServiceImpl managerService;

    public ManagerController(ManagerServiceImpl managerService) {
        this.managerService = managerService;
    }

    @Override
    public Response<Manager> createUser(Map<String, String> request) {
        Manager manager = Manager.builder()
                .firstName(request.get("firstName"))
                .lastName(request.get("lastName"))
                .email(request.get("email"))
                .employeeNumber(Integer.parseInt(request.get("employeeNumber")))
                .build();

        Manager createdManager = managerService.create(manager);

        return new Response<>(true
        , "Successfully created manager"
        , createdManager);
    }

    @Override
    public Response<Manager> deleteUser(Long id) {
        managerService.delete(id);
        return new Response<>(
                true,
                "Successfully deleted manager"
        );
    }

    @Override
    public Response<Manager> updateUser(Long id, Map<String, String> request) {
        Manager updatedManager = Manager.builder()
                .firstName(request.get("firstName"))
                .lastName(request.get("lastName"))
                .email(request.get("email"))
                .employeeNumber(Integer.parseInt(request.get("employeeNumber")))
                .build();
        Manager updatedManagerResponse = managerService.update(id, updatedManager);
        return new Response<>(
                true,
                "Successfully updated manager",
                updatedManagerResponse
        );
    }

    @Override
    public Response<Manager> getUser(Long id) {
        Manager retrievedManager = managerService.findById(id);
        return new Response<>(
                true
                , "Successfully retrieved manager"
                , retrievedManager
        );
    }

    @Override
    public Response<List<Manager>> getAllUsers() {
        List<Manager> managersList = managerService.findAll();
        return new Response<List<Manager>>(
                true
                , "Successfully retrieved managers list"
                , managersList
        );
    }
}
