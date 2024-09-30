package org.ecommerce.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ecommerce.enums.Error;
import org.ecommerce.enums.HttpStatusCode;
import org.ecommerce.logs.Log;
import org.ecommerce.models.*;
import org.ecommerce.services.PasswordService;
import org.ecommerce.services.UserService;
import org.ecommerce.util.JsonParser;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

public class ManagerController extends AbstractUserController implements ControllerOperations<Manager, Long> {

    private final UserService<Manager> managerService;

    public ManagerController(UserService<Manager> managerService, PasswordService passwordService) {
        super(passwordService);
        this.managerService = managerService;
    }

    @Override
    public Response<Manager> create(String jsonRequest) {
        return parseJson(jsonRequest)
                .map(res ->
                        new Response<>(
                                true,
                                "Manager created successfully",
                                managerService.create(res),
                                HttpStatusCode.CREATED))
                .orElse(new Response<>(
                        false,
                        Error.INVALID_REQUEST_FORMAT.getDescription(),
                        HttpStatusCode.BAD_REQUEST
                ));
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
    public Response<Manager> update(String jsonRequest, Long id) {
        return parseJson(jsonRequest)
                .map( res -> new Response<>(
                        true,
                        "Manager updated successfully",
                        managerService.update(id, res),
                        HttpStatusCode.OK
                ))
                .orElse(new Response<>(
                        false,
                        Error.INVALID_REQUEST_FORMAT.getDescription(),
                        HttpStatusCode.BAD_REQUEST
                ));
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
    public Response<List<Manager>> getAll() {
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
            return Optional.of(JsonParser.parseManager(json));
        } catch (JsonProcessingException jsonProcessingException) {
            Log.error(jsonProcessingException.getMessage());
            return Optional.empty();
        }
    }
}
