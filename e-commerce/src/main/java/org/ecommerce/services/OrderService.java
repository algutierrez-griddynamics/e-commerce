package org.ecommerce.services;


import org.ecommerce.models.Order;

public interface OrderService extends OperationsService<Order, Long> { //TODO: Q: Should i refactor this interface to something like OperationsService<RequestDTO, ResponseDTO, ID> ?
}
