package org.ecommerce.services.jpa;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.ecommerce.logs.Log;
import org.ecommerce.models.Customer;
import org.ecommerce.models.Order;
import org.h2.tools.Server;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Calculate period between versions
 * @deprecated
 * This method is no longer acceptable to compute time between versions.
 * <p> Use {@link Utils#calculatePeriod(Machine)} instead.
 *
 * @param machine instance
 * @return computed time
 */
@Deprecated
@SpringBootTest
//@Disabled
//@Transactional
class CustomerServiceImplTest {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    private EntityManager entityManager;

    @MockBean
    private Customer customer;


    @DisplayName("This BeforeAll method creates a web server in the 8082 port so we can connect to the h2 database")
    @BeforeAll
    public static void initTest() throws SQLException {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
                .start();
    }

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setPhoneNumber("1234567890");
    }

    @Test @DisplayName("Save Parent with an initialized ID using repository.save()")
    void saveCustomerRepository() {
        customer.setId(10L);

        customerServiceImpl.create(customer);
    }

    @Test @DisplayName("Save Parent with an initialized ID using entityManager.persist()")
    void saveCustomerEntityManager() {
        customer.setId(2L);

        entityManager.persist(customer);
    }

    @Test @DisplayName("Save Parent with an initialized ID using entityManager.merge()")
    void saveCustomerMerge() {
        customer.setId(3L);

        entityManager.merge(customer);
    }

    @Test @DisplayName("Save Parent without an initialized ID using repository.save()")
    void saveCustomerRepositoryWithoutId() {
        customerServiceImpl.create(customer);
    }

    @Test @DisplayName("Save Parent without an initialized ID using entityManager.persist()")
    void saveCustomerEntityManagerWithoutId() {
        entityManager.persist(customer);
    }

    @Test @DisplayName("Save Parent without an initialized ID using entityManager.merge()")
    void saveCustomerMergeWithoutId() {
        entityManager.merge(customer);
    }

    @Test @DisplayName("Insert Parent with some ID to the database. Save another Parent with the same ID using repository.save()")
    void saveCustomerSameIdRepository() {
        createCustomerWithFixedId();
        customerServiceImpl.create(customer);
    }

    @Test @DisplayName("Insert Parent with some ID to the database. Save another Parent with the same ID entityManager.persist()")
    void saveCustomerSameIdEntityManagerPersist() {
        createCustomerWithFixedId();
        entityManager.persist(customer);
    }

    @Test @DisplayName("Insert Parent with some ID to the database. Save another Parent with the same ID using entityManager.merge().")
    void saveCustomerSameIdEntityManagerMerge() {
        createCustomerWithFixedId();
        entityManager.merge(customer);
    }

    @Test @DisplayName("Save Parent with Children, which are not present in the database - using repository.save()")
    void saveCustomerWrongOrderRepository() {
        Order order = Order.builder().id(99L).build();
        customer.setOrdersHistory(List.of(order));
        customerServiceImpl.create(customer);
    }

    @Test @DisplayName("Save Parent with Children, which are not present in the database - using entityManager.persist()")
    void saveCustomerWrongOrderEntityManagerPersist() {
        Order order = Order.builder().id(99L).build();
        customer.setOrdersHistory(List.of(order));
        entityManager.persist(customer);
    }

    @Test @DisplayName("Save Parent with Children, which are not present in the database - using entityManager.merge().")
    void saveCustomerWrongOrderEntityManagerMerge() {
        Order order = Order.builder().id(99L).build();
        customer.setOrdersHistory(List.of(order));
        entityManager.merge(customer);
    }

    @Test @DisplayName("Save Parent with Children, which ARE present in the database - using repository.save()")
    void saveCustomerRightOrderRepository() {
        //Attach entity
        List<Order> orders = orderServiceImpl.findAll();
        Order order = orders.get(0);

        Log.info(order.toString());
        customer.setOrdersHistory(List.of(order));
        customerServiceImpl.create(customer);
        assertNotNull(customer.getId());
    }

    @Test @DisplayName("Save Parent with Children, which ARE present in the database - using entityManager.persist()")
//    @Transactional
    void saveCustomerWrongRightEntityManagerPersist() {
        //Attach entity
        List<Order> orders = orderServiceImpl.findAll();
        Order order = orders.get(0);

        customer.setOrdersHistory(List.of(order));
        entityManager.persist(customer);

        assertNotNull(customer.getId());
    }

    @Test @DisplayName("Save Parent with Children, which ARE present in the database - using entityManager.merge().")
//    @Transactional
    void saveCustomerRightOrderEntityManagerMerge() {
        //Attach entity
        List<Order> orders = orderServiceImpl.findAll();
        Order order = orders.get(0);

        customer.setOrdersHistory(List.of(order));
        entityManager.merge(customer);

        assertNotNull(customer.getId());
    }


    @Test @DisplayName("Save child without parent -  using repository.save()")
    void saveOrderWithoutCustomerRepository() {
        Order order = Order.builder().id(1L).build();

        orderServiceImpl.create(order);

        assertDoesNotThrow(() -> customerServiceImpl.create(customer));
    }

    @Test @DisplayName("Save child without parent - using entityManager.persist()")
    @Transactional
    void saveOrderWithoutCustomerManagerPersist() {
        //Attach entity
        Order order = Order.builder().id(1L).build();

        entityManager.persist(order);

        assertDoesNotThrow(() -> orderServiceImpl.findById(order.getId()));
    }

    @Test @DisplayName("Save child without parent - using entityManager.merge().")
//    @Transactional
    void saveOrderWithoutCustomerManagerMerge() {
        Order order = Order.builder().id(1L).build();

        entityManager.merge(order);

        assertDoesNotThrow(() -> orderServiceImpl.findById(order.getId()));
    }


    @Test @DisplayName("Save Child with Parent initialized, but not present in the database  -  using repository.save()")
    void saveOrderWithNonExistentCustomerRepository() {
        Order order = Order.builder().build();
        order.setCustomer(Customer.builder().id(99L).build());

        orderServiceImpl.create(order);

        assertDoesNotThrow(() -> customerServiceImpl.create(customer));
        assertNotNull(order.getId());
    }

    @Test @DisplayName("Save Child with Parent initialized, but not present in the database  - using entityManager.persist()")
    @Transactional
    void saveOrderWithNonExistentCustomerEntityManagerPersist() {
        Order order = Order.builder().build();
        order.setCustomer(Customer.builder().id(99L).build());

        entityManager.persist(order);

        assertDoesNotThrow(() -> orderServiceImpl.findById(order.getId()));
        assertNotNull(order.getId());
    }

    @Test @DisplayName("Save Child with Parent initialized, but not present in the database  - using entityManager.merge().")
    @Transactional
    void saveOrderWithNonExistentCustomerEntityManagerMerge() {
        Order order = Order.builder().build();
        order.setCustomer(Customer.builder().id(99L).build());

        entityManager.merge(order);

        assertDoesNotThrow(() -> orderServiceImpl.findById(order.getId()));
        assertNotNull(order.getId());
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

    private void createCustomerWithFixedId() {
//        customerServiceImpl.deleteAll();
        final Long fixedId = 1L;

        Customer newCustomer = new Customer();
        newCustomer.setPhoneNumber("1234567890");
        newCustomer.setId(fixedId);

        customer.setId(fixedId);

        if(Objects.isNull(customerServiceImpl.findById(fixedId))){
            customerServiceImpl.create(newCustomer);
        }
    }
}