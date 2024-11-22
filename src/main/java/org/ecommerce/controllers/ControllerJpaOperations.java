package org.ecommerce.controllers;

import jakarta.validation.Valid;
import org.ecommerce.models.requests.CreateRequest;
import org.ecommerce.models.requests.UpdateRequest;
import org.ecommerce.models.services.responses.GetAllOrdersResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ControllerJpaOperations <REQUEST, ID> {

        ResponseEntity<?> create(@Valid @RequestBody CreateRequest<REQUEST> request);

        void delete(@PathVariable ID orderId);

        ResponseEntity<?> update(@RequestBody UpdateRequest<REQUEST, Long> request);

        GetAllOrdersResponse get();

        ResponseEntity<?> get(@PathVariable ID orderId);
}
