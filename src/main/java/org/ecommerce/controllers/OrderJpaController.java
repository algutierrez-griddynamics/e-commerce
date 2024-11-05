package org.ecommerce.controllers;

import org.ecommerce.dtos.requests.OrderRequestDTO;
import org.ecommerce.dtos.responses.OrderDTO;
import org.ecommerce.models.Response;
import org.ecommerce.models.requests.*;
import org.ecommerce.services.jpa.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderJpaController { //implements ControllerOperations<OrderRequestDTO, Long>{ // TODO: Q: Should i refactor this interface to something like ControllerOperations<RequestDTO, ResponseDTO, ID> ?

    private final OrderServiceImpl orderService;

    public OrderJpaController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

//    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderDTO> create(@PathVariable CreateRequest<OrderRequestDTO> request) {
        OrderDTO response = orderService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @Override
    public ResponseEntity<OrderDTO> delete(DeleteRequest<Long> request) {
        return null;
    }

//    @Override
    public ResponseEntity<OrderDTO> update(UpdateRequest<OrderDTO, Long> request) {
        return null;
    }

//    @Override
    public ResponseEntity<OrderDTO> get(GetRequest<Long> request) {
        return null;
    }

//    @Override
    public ResponseEntity<List<OrderDTO>> getAll(GetAllRequest request) {
        return null;
    }
}
