package org.ecommerce.services.jpa.impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.ecommerce.dtos.requests.OrderRequestDTO;
import org.ecommerce.dtos.responses.OrderDTO;
import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.mappers.BuildOrderFromDTORequest;
import org.ecommerce.mappers.OrderDTOMapper;
import org.ecommerce.models.*;
import org.ecommerce.models.requests.CreateRequest;
import org.ecommerce.models.requests.UpdateRequest;
import org.ecommerce.models.services.responses.*;
import org.ecommerce.repositories.jpa.OrderJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderJpaServiceImpl implements OrderJpaService<OrderRequestDTO, Long> {

    private final OrderJpaRepository orderRepository;
    private final OrderDTOMapper orderDTOMapper;
    private final BuildOrderFromDTORequest buildOrderFromDTORequest;
    private final EntityManager entityManager;

    public OrderJpaServiceImpl(OrderJpaRepository orderRepository, OrderDTOMapper orderDTOMapper, BuildOrderFromDTORequest buildOrderFromDTORequest,  EntityManager entityManager) {
        this.orderRepository = orderRepository;
        this.orderDTOMapper = orderDTOMapper;
        this.buildOrderFromDTORequest = buildOrderFromDTORequest;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public CreateOrderResponse create(CreateRequest<OrderRequestDTO> entity) {
        OrderRequestDTO order = entity.getData();
        Order newOrder = buildOrderFromDTORequest.apply(order);


        Order savedOrder = orderRepository.save(newOrder);
        entityManager.clear();

        return new CreateOrderResponse((findById(savedOrder.getId())).getOrderDTO());
    }


    @Override
    @Transactional
    public UpdateOrderResponse update(UpdateRequest<OrderRequestDTO, Long> entity) {
        Long id = entity.getId();
        OrderDTO existingOrder = findById(id).getOrderDTO();

        if (existingOrder == null) {
            throw new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription());
        }

        Order updatedOrder = buildOrderFromDTORequest.apply(entity.getData());
        updatedOrder.setId(id);

        orderRepository.saveAndFlush(updatedOrder);
        entityManager.clear();

        return new UpdateOrderResponse(findById(updatedOrder.getId()).getOrderDTO());
    }

    @Override
    public void delete(Long id) {
        if (Objects.nonNull(findById(id))) {
            orderRepository.deleteById(id);
        } else {
            throw new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription());
        }
    }

    @Override
    public GetAllOrdersResponse findAll() {
        return new GetAllOrdersResponse(
                orderRepository.findAll().stream()
                        .map(order -> {
                            orderDTOMapper.apply(order);
                            return orderDTOMapper.apply(order);
                        }).collect(Collectors.toList())
        );
    }

    @Override
    public GetOrderResponse findById(Long id) {
        return new GetOrderResponse(
                orderRepository.findById(id).map(orderDTOMapper)
                        .orElseThrow(() -> new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription()))
        );
    }
}
