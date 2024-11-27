package org.ecommerce.models.services.responses;

import org.ecommerce.dtos.responses.OrderDTO;

public class GetOrderResponse extends OrderResponseBase{
    public GetOrderResponse(OrderDTO orderDTO) {
        super(orderDTO);
    }

    public OrderDTO getOrderDTO() {
        return super.orderDTO;
    }
}
