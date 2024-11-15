package org.ecommerce.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ecommerce.enums.HttpStatusCode;
import org.ecommerce.logs.Log;
import org.ecommerce.models.Order;
import org.ecommerce.models.Response;
import org.ecommerce.models.requests.*;
import org.ecommerce.services.OrderService;
import org.ecommerce.services.impl.OrderServiceImpl;
import org.ecommerce.util.JsonParser;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Optional;

// This controller works with JDBC, hence is annotated with dev
@Profile("dev")
public class OrderController implements ControllerOperations <Order, Long> {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Response<Order> create(CreateRequest<Order> request) {
        Order order = request.getData();

        return new Response<>(
                true,
                "Order placed successfully",
                orderService.create(order),
                HttpStatusCode.CREATED);
    }

    @Override
    public Response<Order> delete(DeleteRequest<Long> request) {
        Long id = request.getId();

        orderService.delete(id);

        return new Response<>(true,
                "Order deleted successfully",
                HttpStatusCode.OK);
    }

    @Override
    public Response<Order> update(UpdateRequest<Order, Long> request) {
        return new Response<>(false,
                "Not Supported Operation",
                HttpStatusCode.SERVICE_UNAVAILABLE);
    }

    @Override
    public Response<Order> get(GetRequest<Long> request) {
        Long id = request.getId();

        return new Response<>(true,
                "Order retrieved successfully",
                orderService.findById(id),
                HttpStatusCode.OK);
    }

    @Override
    public Response<List<Order>> getAll(GetAllRequest getAllRequest) {
        return new Response<>(
                true,
                "Orders retrieved succesfully",
                orderService.findAll(),
                HttpStatusCode.ACCEPTED);
    }

    private Optional<Order> parseJson(String json) {
        try {
            return Optional.of(JsonParser.parseJson(json, Order.class));
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
