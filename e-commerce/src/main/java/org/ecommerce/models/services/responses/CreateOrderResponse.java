package org.ecommerce.models.services.responses;

import lombok.NoArgsConstructor;
import org.ecommerce.dtos.responses.OrderDTO;

@NoArgsConstructor
public class CreateOrderResponse extends OrderResponseBase {
    public CreateOrderResponse(OrderDTO orderDTO) {
        super(orderDTO);
    }

    public OrderDTO getOrderDTO() {
        return super.orderDTO;
    }

    // Properties
}
