package org.ecommerce.repositories.inmemory;

import org.ecommerce.enums.OrderStatus;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.logs.Log;
import org.ecommerce.models.*;
import org.ecommerce.models.Order;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ActiveProfiles("dev")
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
    @org.junit.jupiter.api.Order(1)
    void save() {
        Long lastIdBefore = getLastId();

        assertDoesNotThrow(() -> orderRepository.save(order));

        Long lastIdAfter = getLastId();
        assert(lastIdAfter - lastIdBefore == 1);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void findById() {
        assertDoesNotThrow(() -> orderRepository.save(order));

        Long lastIdAfter = getLastId();
        orderRepository.findById(lastIdAfter)
                       .ifPresent(Assertions::assertNotNull);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void deleteById() {
        orderRepository.save(order);
        Long lastId = getLastId();
        assertDoesNotThrow(() -> orderRepository.deleteById(lastId));
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void deleteByIdThrowException() {
        assertThrows(EntityNotFound.class, () -> orderRepository.deleteById(99L));
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void findAll() {
        var orders = orderRepository.findAll();
        assertNotNull(orders);
        assertDoesNotThrow(() -> orderRepository.findAll());
        orders.forEach(order -> Log.info(order.getId().toString()));
    }

    private Long getLastId() {
        List<Order> orders = orderRepository.findAll();
        orders.sort(Comparator.comparing(Order::getId));
        return orders.get(orders.size() - 1).getId();
    }
}