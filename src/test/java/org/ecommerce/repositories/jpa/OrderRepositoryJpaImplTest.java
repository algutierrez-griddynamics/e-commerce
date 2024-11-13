package org.ecommerce.repositories.jpa;

import org.ecommerce.enums.OrderStatus;
import org.ecommerce.models.*;
import org.ecommerce.models.Order;
import org.ecommerce.util.tests.OrderUtils;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ActiveProfiles("local")
@SpringBootTest
class OrderJpaRepositoryTest {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

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

    @DisplayName("This BeforeAll method creates a web server in the 8082 port so we can connect to the h2 database")
    @BeforeAll
    public static void initTest() throws SQLException {
//        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
//                .start();
    }

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
        Order expectedOrder = OrderUtils.buildOrder();

        Order orderCreated = orderJpaRepository.save(expectedOrder);

        assertAll(() -> {
            assertNotNull(orderCreated.getId());
            assertEquals(expectedOrder.getOrderDate().toString(), orderCreated.getOrderDate().toString());
            assertEquals(expectedOrder.getTotalUsd(), orderCreated.getTotalUsd());
            assertEquals(expectedOrder.getStatus(), orderCreated.getStatus());
            assertEquals(expectedOrder.getCustomer().getId(), orderCreated.getCustomer().getId());
            assertEquals(expectedOrder.getShippingInformation().getId(), orderCreated.getShippingInformation().getId());
            assertEquals(expectedOrder.getBillingInformation().getId(), orderCreated.getBillingInformation().getId());
            assertEquals(expectedOrder.getPaymentDetails().getId(), orderCreated.getPaymentDetails().getId());
        });
    }

    @Test
    void update() {
        Long id = getSomeId();
        Order newOrder = orderJpaRepository.findById(id).get();

        newOrder.setStatus(OrderStatus.PLACED);

        Order updatedOrder =  orderJpaRepository.save(newOrder);

        assertEquals(OrderStatus.PLACED, updatedOrder.getStatus());
    }

    @Test
    void delete() {
        Long id = getSomeId();
        orderJpaRepository.deleteById(id);
        assertThrows(NoSuchElementException.class, ()
                -> orderJpaRepository.findById(id).get());
    }

    @Test
    void findAll() {
        List<Order> allOrders = orderJpaRepository.findAll();
        assertNotNull(allOrders);
    }

    @Test
    void findById() {
        Long id = getSomeId();
        Order orderFound = orderJpaRepository.findById(id).get();
        assertEquals(orderFound.getId(), id);
    }

    @Test
    void findByIdNotFoundException() {
        assertThrows(NoSuchElementException.class, () -> orderJpaRepository.findById(999L).get());
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
        orderJpaRepository.save(order);
        List<Order> allOrders = orderJpaRepository.findAll();
        allOrders.sort(Comparator.comparing(Order::getId));
        return allOrders.get(allOrders.size() - 1).getId();
    }
}



