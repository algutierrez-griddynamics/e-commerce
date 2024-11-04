package org.ecommerce.services.impl;

import org.ecommerce.enums.Error;
import org.ecommerce.enums.OrderStatus;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.message.broker.MessageQueue;
import org.ecommerce.message.broker.consumers.OrderConsumer;
import org.ecommerce.message.broker.producers.Producer;
import org.ecommerce.models.Order;
import org.ecommerce.repositories.inmemory.OrderRepository;
import org.ecommerce.services.OrderService;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MessageQueue<Order> messageQueue;
    private final ExecutorService executorService;

    public OrderServiceImpl(OrderRepository orderRepository, MessageQueue<Order> messageQueue) {
        this.orderRepository = orderRepository;
        this.messageQueue = messageQueue;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public Order create(Order order) {
        orderRepository.save(order);
        produceOrder(order);
        //orderRepository.updateStatus(order, OrderStatus.REQUESTED);
        updateOrderStatus(order.getId(), OrderStatus.REQUESTED);
        return order;
    }

    @Override
    public Order update(Order entity, Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
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

    // Consume message broker messages
    public void startOrderProcessing() {
        executorService.submit(new OrderConsumer(messageQueue));
    }

    public void stopOrderProcessing() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1, java.util.concurrent.TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public void produceOrder(Order order) {
        Producer<Order> producer = new Producer<>(messageQueue, order);
        producer.run();
    }

    // Shipping Management connects to this service to update the status
    public void updateOrderStatus(Long id, OrderStatus status) {
        orderRepository.findById(id)
                .map(result -> {
                    orderRepository.updateStatus(result, status);
                    return result;
                }).orElseThrow(() -> new EntityNotFound("Order not found"));
    }
}
