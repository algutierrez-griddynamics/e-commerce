package org.ecommerce.controllers;

import org.ecommerce.models.Order;
import org.ecommerce.models.Response;

import java.util.List;
import java.util.Map;

public class OrderController implements ControllerOperations<Order, Long> {
    @Override
    public Response<Order> create(Map<String, String> request) {
        return null;
    }

    @Override
    public Response<Order> delete(Long aLong) {
        return null;
    }

    @Override
    public Response<Order> update(Long aLong, Map<String, String> request) {
        return null;
    }

    @Override
    public Response<Order> get(Long aLong) {
        return null;
    }

    @Override
    public Response<List<Order>> get() {
        return null;
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
}
