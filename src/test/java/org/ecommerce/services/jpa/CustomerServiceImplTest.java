package org.ecommerce.services.jpa;

import jakarta.persistence.EntityManager;
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



@SpringBootTest
@Disabled
//@Transactional
class CustomerServiceImplTest {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

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
        customer.setId(1L);

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

    @Test @DisplayName("Insert Parent with some ID to the database. Save another Parent with the same ID using repository.save()")
    void saveCustomerWrongOrderRepository() {
        Order order = Order.builder().id(99L).build();
        customer.setOrdersHistory(List.of(order));
        customerServiceImpl.create(customer);
    }

    @Test @DisplayName("Insert Parent with some ID to the database. Save another Parent with the same ID entityManager.persist()")
    void saveCustomerWrongOrderEntityManagerPersist() {
        Order order = Order.builder().id(99L).build();
        customer.setOrdersHistory(List.of(order));
        entityManager.persist(customer);
    }

    @Test @DisplayName("Insert Parent with some ID to the database. Save another Parent with the same ID using entityManager.merge().")
    void saveCustomerWrongOrderEntityManagerMerge() {
        Order order = Order.builder().id(99L).build();
        customer.setOrdersHistory(List.of(order));
        entityManager.merge(customer);
    }

    @Test
    @Disabled("This 'test' helps us to lock the current thread, allowing us to connect to the h2 instance while running the tests")
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