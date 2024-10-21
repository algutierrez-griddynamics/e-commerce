package org.ecommerce.services.jpa;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.ecommerce.models.Customer;
import org.h2.tools.Server;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;



@SpringBootTest
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
}