package org.ecommerce.controllers;

import jakarta.validation.Valid;
import org.ecommerce.enums.AvailableOrderByFields;
import org.ecommerce.enums.AvailableOrderByOptions;
import org.ecommerce.models.requests.CreateRequest;
import org.ecommerce.models.requests.UpdateRequest;
import org.ecommerce.models.services.responses.GetAllOrdersResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

public interface ControllerJpaOperations <REQUEST, ID> {

        ResponseEntity<?> create(@Valid @RequestBody CreateRequest<REQUEST> request);

        void delete(@PathVariable ID orderId);

        ResponseEntity<?> update(@RequestBody UpdateRequest<REQUEST, Long> request);

        GetAllOrdersResponse get(
                @RequestParam(required = false) String firstName,
                @RequestParam(required = false) String city,
                @RequestParam(required = false) Long customerId,
                @RequestParam(required = false) String orderStatus,
                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startDate,
                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endDate,
                @RequestParam(required = false) Integer page,
                @RequestParam(required = false) Integer size,
                @RequestParam(required = false) AvailableOrderByOptions direction,
                @RequestParam(required = false) AvailableOrderByFields sort
        );

        ResponseEntity<?> get(@PathVariable ID orderId);
}
