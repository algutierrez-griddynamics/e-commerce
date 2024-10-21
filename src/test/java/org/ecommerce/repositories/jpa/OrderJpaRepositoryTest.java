package org.ecommerce.repositories.jpa;

import jakarta.persistence.EntityManager;
import org.ecommerce.enums.OrderStatus;
import org.ecommerce.models.*;
import org.ecommerce.models.Order;
import org.ecommerce.services.jpa.OrderServiceImpl;
import org.h2.tools.Server;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ActiveProfiles("local")
@SpringBootTest
class OrderJpaRepositoryTest {

    @Autowired
    EntityManager entityManager;

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

    @Mock
    private BillingInformation billingInformation;

    @Mock
    private PaymentDetails paymentDetails;

    @DisplayName("This BeforeAll method creates a web server in the 8082 port so we can connect to the h2 database")
    @BeforeAll
    public static void initTest() throws SQLException {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
                .start();
    }

    @BeforeEach
    void setUp() {
        orderService.deleteAll();

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


    @Test @DisplayName("Save Parent with an initialized ID using repository.save()")
    void saveOrderRepository() {
        when(order.getId()).thenReturn(1L);

        orderService.create(order);

    }

    @Test @DisplayName("Save Parent with an initialized ID using entityManager.persist()")
    void saveOrderEntityManager() {
        when(order.getId()).thenReturn(2L);

        entityManager.persist(order);
    }

    @Test @DisplayName("Save Parent with an initialized ID using entityManager.merge()")
    void saveOrderMerge() {
        when(order.getId()).thenReturn(3L);

        entityManager.merge(order);
    }

    @Test
//    @Disabled("This 'test' helps us to lock the current thread, allowing us to connect to the h2 instance while running the tests")
    public void infiniteLoop() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}