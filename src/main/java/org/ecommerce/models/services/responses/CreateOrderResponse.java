package org.ecommerce.models.services.responses;

import org.ecommerce.dtos.responses.OrderDTO;

public class CreateOrderResponse extends OrderResponseBase {
    public CreateOrderResponse(OrderDTO orderDTO) {
        super(orderDTO);
    }

    public OrderDTO getOrderDTO() {
        return super.orderDTO;
    }

    // Properties
}
