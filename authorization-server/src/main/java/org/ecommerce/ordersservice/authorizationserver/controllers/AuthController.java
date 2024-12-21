package org.ecommerce.ordersservice.authorizationserver.controllers;

import org.ecommerce.ordersservice.authorizationserver.models.AuthRequest;
import org.ecommerce.ordersservice.authorizationserver.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController (AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        return authService.validateCredentials(request);
    }

    @GetMapping("/keys")
    public ResponseEntity<Set<String>> getKeys() {
        return authService.getKeys();
    }

}
