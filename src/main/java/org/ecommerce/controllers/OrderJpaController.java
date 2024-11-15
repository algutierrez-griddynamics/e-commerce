package org.ecommerce.controllers;

import jakarta.validation.Valid;
import org.ecommerce.dtos.requests.OrderRequestDTO;
import org.ecommerce.models.services.responses.CreateOrderResponse;
import org.ecommerce.models.requests.*;
import org.ecommerce.models.services.responses.GetAllOrdersResponse;
import org.ecommerce.models.services.responses.GetOrderResponse;
import org.ecommerce.models.services.responses.UpdateOrderResponse;
import org.ecommerce.services.jpa.OrderJpaService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Profile("local")
@RestController
public class OrderJpaController implements ControllerJpaOperations <OrderRequestDTO, Long> {

    private final OrderJpaService<OrderRequestDTO, Long> orderService;

    public OrderJpaController(OrderJpaService<OrderRequestDTO, Long> orderService) {
        this.orderService = orderService;
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/orders")
    public ResponseEntity<CreateOrderResponse> create(@RequestBody @Valid CreateRequest<OrderRequestDTO> request) {
        CreateOrderResponse response = orderService.create(request);

        return new ResponseEntity<CreateOrderResponse>(response, HttpStatus.CREATED);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/orders/{orderId}")
    public void delete(@PathVariable Long orderId) {
        orderService.delete(orderId);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/orders")
    public ResponseEntity<UpdateOrderResponse> update(@RequestBody UpdateRequest<OrderRequestDTO, Long> request) {
        UpdateOrderResponse response = orderService.update(request);

        return new ResponseEntity<UpdateOrderResponse>(response, HttpStatus.OK);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/orders")
    public GetAllOrdersResponse get() {
        return orderService.findAll();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/orders/{orderId}")
    public ResponseEntity<GetOrderResponse> get(@PathVariable Long orderId) {
        GetOrderResponse getOrderResponse = orderService.findById(orderId);

        return new ResponseEntity<GetOrderResponse>(getOrderResponse, HttpStatus.OK);
    }

}
