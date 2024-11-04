package org.ecommerce.services.jpa;

import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.models.Order;
import org.ecommerce.repositories.jpa.OrderJpaRepository;
import org.ecommerce.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderJpaRepository orderRepository;

    public OrderServiceImpl(OrderJpaRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(Order entity) {
       return orderRepository.save(entity);
    }

    @Override
    public Order update(Order entity, Long id) {
        if (Objects.nonNull(findById(id))) {
            return orderRepository.save(entity);
        } else {
            throw new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription());
        }
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
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription()));
    }
}
