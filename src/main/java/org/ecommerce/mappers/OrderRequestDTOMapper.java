package org.ecommerce.mappers;

import org.ecommerce.dtos.requests.OrderRequestDTO;
import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.MappingException;
import org.ecommerce.models.Order;

import java.util.Optional;
import java.util.function.Function;

public class OrderRequestDTOMapper implements Function<Order, OrderRequestDTO> {
    @Override
    public OrderRequestDTO apply(Order order) {
        return Optional.of(order)
                .map((o) -> new OrderRequestDTO(
                        o.getStatus(),
                        o.getOrderDate(),
                        o.getTotalUsd(),
                        o.getCustomer().getId(),
                        o.getBillingInformation().getId(),
                        o.getShippingInformation().getId(),
                        o.getPaymentDetails().getId()
                )).orElseThrow(
                        () -> new MappingException(this.getClass().toString() , Error.MAPPING_EXCEPTION.getDescription())
                );
    }
}
