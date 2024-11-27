package org.ecommerce.models.services.responses;

import lombok.Getter;
import org.ecommerce.dtos.responses.OrderDTO;

import java.util.List;

@Getter
public class GetAllOrdersResponse extends PageableModel{
    private List<OrderDTO> orders;

    public GetAllOrdersResponse(List<OrderDTO> orders, int currentPage, int pageSize, long totalElements, int totalPages) {
        super(currentPage, pageSize, totalElements, totalPages);
        this.orders = orders;
    }
}
