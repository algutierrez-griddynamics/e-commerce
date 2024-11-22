package org.ecommerce.models.services.responses;

import lombok.NoArgsConstructor;
import org.ecommerce.dtos.responses.OrderDTO;

@NoArgsConstructor
public abstract class OrderResponseBase {
    protected OrderDTO orderDTO;

    OrderResponseBase(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }
}
