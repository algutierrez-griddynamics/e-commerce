package org.ecommerce.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ecommerce.enums.Error;
import org.ecommerce.enums.HttpStatusCode;
import org.ecommerce.logs.Log;
import org.ecommerce.models.Order;
import org.ecommerce.models.Response;
import org.ecommerce.services.OrderService;
import org.ecommerce.services.impl.OrderServiceImpl;
import org.ecommerce.util.JsonParser;

import java.util.List;
import java.util.Optional;

// TODO: Refactor ControllerOperations for receive a StringJsonRequest, instead of a Map request. Then refactor this class to implement ControllerOperations
public class OrderController { // implements ControllerOperations <Order, Long> {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    @Override
    public Response<Order> create(String jsonRequest) {
        return parseJson(jsonRequest)
                .map(res ->
                        new Response<>(
                                true,
                                "Order placed successfully",
                                orderService.create(res),
                                HttpStatusCode.CREATED))
                .orElse(new Response<>(
                        false,
                        Error.INVALID_REQUEST_FORMAT.getDescription(),
                        HttpStatusCode.BAD_REQUEST
                ));
    }

//    @Override
    public Response<Order> delete(Long id) {
        orderService.delete(id);
        return new Response<>(true,
                "Order deleted successfully",
                HttpStatusCode.OK);
    }

//    @Override
    public Response<Order> update(Long id, String jsonRequest) {
        return new Response<>(false,
                "Not Supported Operation",
                HttpStatusCode.SERVICE_UNAVAILABLE);
    }

//    @Override
    public Response<Order> get(Long id) {
        return new Response<>(true,
                "Order retrieved successfully",
                orderService.findById(id),
                HttpStatusCode.OK);
    }

//    @Override
    public Response<List<Order>> get() {
        return new Response<>(
                true,
                "Orders retrieved succesfully",
                orderService.findAll(),
                HttpStatusCode.ACCEPTED);
    }

//    @Override
    public Optional<Order> parseJson(String json) {
        try {
            return Optional.of(JsonParser.parseOrder(json));
        } catch (JsonProcessingException jsonProcessingException) {
            Log.error(jsonProcessingException.getMessage());
            return Optional.empty();
        }
    }

    public void consumeOrders(){
        OrderServiceImpl orderServiceImpl = (OrderServiceImpl) orderService;
        orderServiceImpl.startOrderProcessing();
    }
}
