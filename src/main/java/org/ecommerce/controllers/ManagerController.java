package org.ecommerce.controllers;

import org.ecommerce.enums.HttpStatusCode;
import org.ecommerce.models.*;
import org.ecommerce.models.requests.*;
import org.ecommerce.services.PasswordService;
import org.ecommerce.services.UserService;

import java.util.List;

public class ManagerController extends AbstractUserController implements ControllerOperations<Manager, Long> {

    private final UserService<Manager> managerService;

    public ManagerController(UserService<Manager> managerService, PasswordService passwordService) {
        super(passwordService);
        this.managerService = managerService;
    }

    @Override
    public Response<Manager> create(CreateRequest<Manager> request) {
        Manager manager = request.getData();

        Manager createdManager = managerService.create(manager);

        return new Response<>(true
        , "Successfully created manager"
        , createdManager,
                HttpStatusCode.ACCEPTED);
    }

    @Override
    public Response<Manager> delete(DeleteRequest<Long> request) {
        Long id = request.getId();

        managerService.delete(id);

        return new Response<>(
                true,
                "Successfully deleted manager",
                HttpStatusCode.OK
        );
    }

    @Override
    public Response<Manager> update(UpdateRequest<Manager, Long> request) {
        Manager manager = request.getData();
        Long id = request.getId();

        Manager updatedManagerResponse = managerService.update(manager, id);

        return new Response<>(
                true,
                "Successfully updated manager",
                updatedManagerResponse,
                HttpStatusCode.OK
        );
    }

    @Override
    public Response<Manager> get(GetRequest<Long> request) {
        Long id = request.getId();

        Manager retrievedManager = managerService.findById(id);

        return new Response<>(
                true
                , "Successfully retrieved manager"
                , retrievedManager
                , HttpStatusCode.OK
        );
    }

    @Override
    public Response<List<Manager>> getAll(GetAllRequest request) {
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
        try {
            return Optional.of(JsonParser.parseJson(json, Manager.class));
        } catch (JsonProcessingException jsonProcessingException) {
            Log.error(jsonProcessingException.getMessage());
            return Optional.empty();
        }
    }
}
