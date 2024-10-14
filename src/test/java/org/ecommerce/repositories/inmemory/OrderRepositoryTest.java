package org.ecommerce.repositories.inmemory;

import org.ecommerce.enums.OrderStatus;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.logs.Log;
import org.ecommerce.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @MockBean
    private Order order;

    @MockBean
    private ShippingInformation shippingInformation;

    @MockBean
    private Price price;

    @MockBean
    private PaymentDetails paymentDetails;

    @MockBean
    private BillingInformation billingInformation;

    @MockBean
    private Customer customer;

    @BeforeEach
    void setUp() {
        BigDecimal amount = new BigDecimal("100.00");

        lenient().when(order.getOrderDate()).thenReturn(new Date());

        lenient().when(order.getCustomer()).thenReturn(customer);
        lenient().when(order.getCustomer().getId()).thenReturn(1L);

        lenient().when(order.getShippingInformation()).thenReturn(shippingInformation);
        lenient().when(order.getShippingInformation().getId()).thenReturn(1L);
        lenient().when(order.getShippingInformation().getShippingCost()).thenReturn(price);
        lenient().when(order.getShippingInformation().getShippingCost().getAmount()).thenReturn(amount);

        lenient().when(order.getBillingInformation()).thenReturn(billingInformation);
        lenient().when(order.getBillingInformation().getId()).thenReturn(1L);
        lenient().when(order.getBillingInformation().getAmount()).thenReturn(price);
        lenient().when(order.getBillingInformation().getAmount().getAmount()).thenReturn(amount);

        lenient().when(order.getPaymentDetails()).thenReturn(paymentDetails);
        lenient().when(order.getPaymentDetails().getId()).thenReturn(1L);

        lenient().when(order.getStatus()).thenReturn(OrderStatus.PLACED);
    }

    @Test
    void save() {
        assertDoesNotThrow(() -> orderRepository.save(order));
    }


    @Test
    void findById() {
       orderRepository.findById(3L)
                       .ifPresent(Assertions::assertNotNull);

        assertDoesNotThrow(() -> orderRepository.findById(3L));
    }

    @Test
    void deleteById() {
        assertDoesNotThrow(() -> orderRepository.deleteById(3L));
    }

    @Test
    void deleteByIdThrowException() {
        assertThrows(EntityNotFound.class, () -> orderRepository.deleteById(99L));
    }

    @Test
    void findAll() {
        var orders = orderRepository.findAll();
        assertNotNull(orders);
        assertDoesNotThrow(() -> orderRepository.findAll());
        orders.forEach(order -> Log.info(order.getId().toString()));
    }
}