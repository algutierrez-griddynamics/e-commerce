package org.ecommerce.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ecommerce.enums.HttpStatusCode;
import org.ecommerce.logs.Log;
import org.ecommerce.models.*;
import org.ecommerce.models.requests.*;
import org.ecommerce.services.PasswordService;
import org.ecommerce.services.UserService;
import org.ecommerce.util.JsonParser;

import java.util.List;
import java.util.Optional;

public class ManagerController extends AbstractUserController implements ControllerOperations<Employee, Long> {

    private final UserService<Employee> managerService;

    public ManagerController(UserService<Employee> managerService, PasswordService passwordService) {
        super(passwordService);
        this.managerService = managerService;
    }

    @Override
    public Response<Employee> create(CreateRequest<Employee> request) {
        Employee manager = request.getData();

        Employee createdManager = managerService.create(manager);

        return new Response<>(true
        , "Successfully created manager"
        , createdManager,
                HttpStatusCode.ACCEPTED);
    }

    @Override
    public Response<Employee> delete(DeleteRequest<Long> request) {
        Long id = request.getId();

        managerService.delete(id);

        return new Response<>(
                true,
                "Successfully deleted manager",
                HttpStatusCode.OK
        );
    }

    @Override
    public Response<Employee> update(UpdateRequest<Employee, Long> request) {
        Employee manager = request.getData();
        Long id = request.getId();

        Employee updatedManagerResponse = managerService.update(manager, id);

        return new Response<>(
                true,
                "Successfully updated manager",
                updatedManagerResponse,
                HttpStatusCode.OK
        );
    }

    @Override
    public Response<Employee> get(GetRequest<Long> request) {
        Long id = request.getId();

        Employee retrievedManager = managerService.findById(id);

        return new Response<>(
                true
                , "Successfully retrieved manager"
                , retrievedManager
                , HttpStatusCode.OK
        );
    }

    @Override
    public Response<List<Employee>> getAll(GetAllRequest request) {
        List<Employee> managersList = managerService.findAll();
        return new Response<List<Employee>>(
                true
                , "Successfully retrieved managers list"
                , managersList
                , HttpStatusCode.OK
        );
    }

    public Optional<Employee> parseJson(String json) {
        try {
            return Optional.of(JsonParser.parseJson(json, Employee.class));
        } catch (JsonProcessingException jsonProcessingException) {
            Log.error(jsonProcessingException.getMessage());
            return Optional.empty();
        }
    }
}
