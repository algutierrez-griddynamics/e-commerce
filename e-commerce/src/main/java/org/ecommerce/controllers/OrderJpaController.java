package org.ecommerce.controllers;

import jakarta.validation.Valid;
import org.ecommerce.dtos.requests.OrderRequestDTO;
import org.ecommerce.enums.AvailableOrderByFields;
import org.ecommerce.enums.AvailableOrderByOptions;
import org.ecommerce.models.services.responses.CreateOrderResponse;
import org.ecommerce.models.requests.*;
import org.ecommerce.models.services.responses.GetAllOrdersResponse;
import org.ecommerce.models.services.responses.GetOrderResponse;
import org.ecommerce.models.services.responses.UpdateOrderResponse;
import org.ecommerce.services.jpa.OrderJpaService;
import org.ecommerce.util.database.specifications.SpecificationParameters;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/orders/{orderId}")
    public void delete(@PathVariable Long orderId) {
        orderService.delete(orderId);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/orders")
    public ResponseEntity<UpdateOrderResponse> update(@RequestBody @Valid UpdateRequest<OrderRequestDTO, Long> request) {
        UpdateOrderResponse response = orderService.update(request);

        return new ResponseEntity<UpdateOrderResponse>(response, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/orders")
    public GetAllOrdersResponse get(
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
    ) {
        Sort.Direction selectedDirection = direction == null
                || direction.name().equals(AvailableOrderByOptions.ASCENDING.name())
                ? Sort.Direction.ASC : Sort.Direction.DESC;

        String selectedSort = sort == null
                ? AvailableOrderByFields.DATE.getFieldName() : sort.getFieldName();

        Pageable pageable = PageRequest.of(
                  page == null ? 0 : page
                , size == null ? 10 : size
                , selectedDirection
                , selectedSort
        );

        SpecificationParameters specs = SpecificationParameters.builder()
                .firstName(firstName)
                .city(city)
                .customerId(customerId)
                .orderStatus(orderStatus)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        return orderService.findAll(specs, pageable);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/orders/{orderId}")
    public ResponseEntity<GetOrderResponse> get(@PathVariable Long orderId) {
        GetOrderResponse getOrderResponse = orderService.findById(orderId);

        return new ResponseEntity<GetOrderResponse>(getOrderResponse, HttpStatus.OK);
    }

}
