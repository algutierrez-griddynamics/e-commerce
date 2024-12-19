package org.ecommerce.ordersservice.authorizationserver.controllers;

import com.nimbusds.jose.JOSEException;
import org.ecommerce.ordersservice.authorizationserver.models.AuthRequest;
import org.ecommerce.ordersservice.authorizationserver.services.AuthControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthControllerService authControllerService;

    public AuthController (AuthControllerService authControllerService) {
        this.authControllerService = authControllerService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) throws JOSEException {
        return authControllerService.validateCredentials(request);
    }

}
