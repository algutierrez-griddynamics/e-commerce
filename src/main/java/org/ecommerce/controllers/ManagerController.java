package org.ecommerce.controllers;

import org.ecommerce.enums.HttpStatusCode;
import org.ecommerce.models.*;
import org.ecommerce.services.impl.ManagerServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ManagerController extends AbstractUserController implements ControllerOperations<Manager, Long> {

    private final ManagerServiceImpl managerService;

    public ManagerController(ManagerServiceImpl managerService) {
        this.managerService = managerService;
    }

    @Override
    public Response<Manager> create(Map<String, String> request) {
        Manager manager = Manager.builder()
                .firstName(request.get("firstName"))
                .lastName(request.get("lastName"))
                .email(request.get("email"))
                .employeeNumber(Integer.parseInt(request.get("employeeNumber")))
                .build();

        Manager createdManager = managerService.create(manager);

        return new Response<>(true
        , "Successfully created manager"
        , createdManager,
                HttpStatusCode.ACCEPTED);
    }

    @Override
    public Response<Manager> delete(Long id) {
        managerService.delete(id);
        return new Response<>(
                true,
                "Successfully deleted manager",
                HttpStatusCode.OK
        );
    }

    @Override
    public Response<Manager> update(Long id, Map<String, String> request) {
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
                updatedManagerResponse,
                HttpStatusCode.OK
        );
    }

    @Override
    public Response<Manager> get(Long id) {
        Manager retrievedManager = managerService.findById(id);
        return new Response<>(
                true
                , "Successfully retrieved manager"
                , retrievedManager
                , HttpStatusCode.OK
        );
    }

    @Override
    public Response<List<Manager>> get() {
        List<Manager> managersList = managerService.findAll();
        return new Response<List<Manager>>(
                true
                , "Successfully retrieved managers list"
                , managersList
                , HttpStatusCode.OK
        );
    }

    @Override
    public Optional<Manager> parseJson(String json) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
