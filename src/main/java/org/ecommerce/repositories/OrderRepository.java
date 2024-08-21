package org.ecommerce.repositories;

import org.ecommerce.models.Order;
import org.ecommerce.util.CrudOperations;

import java.util.List;

public class OrderRepository implements CrudOperations<Order> {


    @Override
    public Order save(Order entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Order findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Order> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
