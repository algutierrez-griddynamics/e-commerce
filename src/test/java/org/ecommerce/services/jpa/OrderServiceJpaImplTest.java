package org.ecommerce.services.jpa;

import org.ecommerce.enums.OrderStatus;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.logs.Log;
import org.ecommerce.models.*;
import org.ecommerce.models.Order;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ActiveProfiles("local")
@SpringBootTest
class OrderServiceJpaImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Mock
    private Order order;

    @Mock
    private Customer customer;

    @Mock
    private ShippingInformation shippingInformation;

    @Mock
    private Price price;

    @Mock()
    private BillingInformation billingInformation;

    @Mock
    private PaymentDetails paymentDetails;

    @BeforeEach
    void setUp() {
        BigDecimal amount = new BigDecimal("100.00");

        lenient().when(order.getOrderDate()).thenReturn(new Date());

        lenient().when(order.getTotalUsd()).thenReturn(amount);

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
    void create() {
        orderService.create(order);
        Log.info(order.getTotalUsd().toString());
        assertNotNull(order.getId());
    }

    @Test
    void update() {
        Long id = getSomeId();
        Order newOrder = new Order();

        newOrder.setStatus(OrderStatus.PLACED);

        Order updatedOrder =  orderService.update(newOrder, id);
        assertEquals(OrderStatus.PLACED, updatedOrder.getStatus());
    }

    @Test
    void delete() {
        Long id = getSomeId();
        orderService.delete(id);
        assertThrows(EntityNotFound.class, ()
                -> orderService.findById(id));
    }

    @Test
    void findAll() {
        List<Order> allOrders = orderService.findAll();
        assertNotNull(allOrders);
    }

    @Test
    void findById() {
        Long id = getSomeId();
        Order orderFound = orderService.findById(id);
        assertEquals(orderFound.getId(), id);
    }

    @Test
    void findByIdNotFoundException() {
        assertThrows(EntityNotFound.class, () -> orderService.findById(999L));
    }

    @Test
    @Disabled("This test prevent tests to finish, used for debug purposes")
    void infiniteLoop() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private Long getSomeId() {
        orderService.create(order);
        List<Order> allOrders = orderService.findAll();
        allOrders.sort(Comparator.comparing(Order::getId));
        return allOrders.get(allOrders.size() - 1).getId();
    }
}



