package org.ecommerce.controllers;

import org.ecommerce.models.Response;
import org.ecommerce.services.PasswordService;

import java.util.Map;

public abstract class AbstractUserController {
    private PasswordService passwordService;

    public AbstractUserController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    public Response<Map<String, String>> changePassword(Map<String, String> request) {
        Response<Map<String, String>> response = new Response<>();

        Map<String, String> result = passwordService.changePassword(
                Long.valueOf(request.get("id")),
                Map.of("oldPassword", request.get("oldPassword"),
                        "newPassword", request.get("newPassword"))
        );

        result.values().stream()
                .filter(res -> res.contains("error"))
                .findAny()
                .ifPresentOrElse(response::setMessage
                        , () -> response.setMessage("Password changed successfully"));
        response.setSuccess(true);
        response.setData(result);
        return response;
    }
}
