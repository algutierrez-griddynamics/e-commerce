package org.ecommerce.models.services.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.ecommerce.dtos.responses.OrderDTO;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllOrdersResponse {
    private List<OrderDTO> orders;
}
