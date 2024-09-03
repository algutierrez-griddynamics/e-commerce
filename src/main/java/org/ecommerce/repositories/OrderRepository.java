package org.ecommerce.repositories;

import org.ecommerce.enums.OrderStatus;
import org.ecommerce.models.Order;
import org.ecommerce.repositories.impl.CrudOperationsImpl;

public class OrderRepository extends CrudOperationsImpl<Order> {
    public void updateStatus(Order order, OrderStatus status) {
        order.setStatus(status);
    }
}
