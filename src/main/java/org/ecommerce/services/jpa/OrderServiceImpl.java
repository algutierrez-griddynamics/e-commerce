package org.ecommerce.services.jpa;

import org.ecommerce.dtos.requests.OrderRequestDTO;
import org.ecommerce.dtos.responses.OrderDTO;
import org.ecommerce.enums.Error;
import org.ecommerce.enums.OrderStatus;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.mappers.OrderDTOMapper;
import org.ecommerce.models.*;
import org.ecommerce.models.requests.CreateRequest;
import org.ecommerce.repositories.jpa.OrderJpaRepository;
import org.ecommerce.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderJpaRepository orderRepository;
    private final OrderDTOMapper orderDTOMapper;

    public OrderServiceImpl(OrderJpaRepository orderRepository, OrderDTOMapper orderDTOMapper) {
        this.orderRepository = orderRepository;
        this.orderDTOMapper = orderDTOMapper;
    }

//    @Override
    public OrderDTO create(CreateRequest<OrderRequestDTO> entity) {
        OrderRequestDTO order = entity.getData();

        Order newOrder = buildOrderFromDTO(order);

        return orderDTOMapper.apply(orderRepository.save(newOrder));
    }


    //    @Override
    public OrderDTO update(CreateRequest<OrderRequestDTO> entity, Long id) {
        OrderDTO existingOrder = findById(id);

        if (existingOrder == null) {
            throw new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription());
        }

        Order updatedOrder = buildOrderFromDTO(entity.getData());
        updatedOrder.setId(id);

        return orderDTOMapper.apply(orderRepository.save(updatedOrder));
    }

//    @Override
    public void delete(Long id) {
        if (Objects.nonNull(findById(id))) {
            orderRepository.deleteById(id);
        } else {
            throw new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription());
        }
    }

//    @Override
    public List<OrderDTO> findAll() {
       return orderRepository.findAll().stream()
               .map(order -> {
                   orderDTOMapper.apply(order);
                   return orderDTOMapper.apply(order);
               }).collect(Collectors.toList());
    }

//    @Override
    public OrderDTO findById(Long id) {
        return orderRepository.findById(id).map(orderDTOMapper)
                .orElseThrow(() -> new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription()));
    }

    private Order buildOrderFromDTO(OrderRequestDTO order) {
        return Order.builder()
                .status(order.status())
                .orderDate(order.date())
                .totalUsd(order.totalUsd())
                .customer(Customer.builder()
                        .id(order.fk_customer_id()).build())
                .billingInformation(BillingInformation.builder()
                        .id(order.fk_billing_information_id()).build())
                .shippingInformation(ShippingInformation.builder()
                        .id(order.fk_shipping_information_id()).build())
                .paymentDetails(PaymentDetails.builder()
                        .id(order.fk_shipping_information_id()).build()
                ).build();
    }
}
