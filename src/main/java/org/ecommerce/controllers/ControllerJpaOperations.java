package org.ecommerce.controllers;

import jakarta.validation.Valid;
import org.ecommerce.models.requests.CreateRequest;
import org.ecommerce.models.requests.UpdateRequest;
import org.ecommerce.models.services.responses.GetAllOrdersResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
                @RequestParam(required = false) LocalDateTime startDate,
                @RequestParam(required = false) LocalDateTime endDate,
                @PageableDefault(sort = "orderDate", direction = Sort.Direction.ASC) Pageable pageable);

        ResponseEntity<?> get(@PathVariable ID orderId);
}
