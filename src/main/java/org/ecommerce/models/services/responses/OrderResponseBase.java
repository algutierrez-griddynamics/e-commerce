package org.ecommerce.models.services.responses;

import org.ecommerce.dtos.responses.OrderDTO;

public abstract class OrderResponseBase {
    protected OrderDTO orderDTO;

    OrderResponseBase(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }
}
