package org.ecommerce.models.services.responses;

import org.ecommerce.dtos.responses.OrderDTO;

public class UpdateOrderResponse extends OrderResponseBase{
    public UpdateOrderResponse(OrderDTO orderDTO) {
        super(orderDTO);
    }

    public OrderDTO getOrderDTO() {
        return super.orderDTO;
    }
}
