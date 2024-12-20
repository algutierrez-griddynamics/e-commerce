package org.ecommerce.api_gateway.feign;

import org.ecommerce.api_gateway.models.AuthRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@FeignClient(name = "AUTHORIZATION-SERVICE")
public interface AuthenticationServiceInterface {

    @PostMapping("/auth/login")
    ResponseEntity<?> login(@RequestBody AuthRequest request);

    @GetMapping("/auth/keys")
    ResponseEntity<Set<String>> getKeys();

}
