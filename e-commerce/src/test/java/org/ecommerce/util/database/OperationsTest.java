package org.ecommerce.util.database;

import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.DatabaseException;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.logs.Log;
import org.ecommerce.models.Customer;
import org.h2.tools.Server;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Tests for Operations class")
//@Import("some.other.config.bean.files")
class OperationsTest {
    @DisplayName("This BeforeAll method creates a web server in the 8082 port so we can connect to the h2 database")
    @BeforeAll
    public static void initTest() throws SQLException {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
                .start();
    }

    @Autowired
    private Operations<Customer> operations;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @DisplayName("Checking database connection")
    @Order(1)
    @Test
    void getConnection() {
        try (Connection connection = operations.getConnection()) {
            assertNotNull(connection);
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @DisplayName("Inserting with execute method")
    @Order(2)
    @Test
    void execute() {
        String email = "foo@bar.com";
        String insertQuery = String.format("INSERT INTO customers (first_name, last_name, email, password, " +
                "phone_number, address, categories_preferences) VALUES ('John', 'Doe', " +
                "?, 'password123', '555-0100', '123 Elm St, Springfield, " +
                "IL', ARRAY['Electronics', 'Books']);", email);
        String deleteQuery = "DELETE FROM customers WHERE email = ?";
        assertDoesNotThrow( () -> operations.execute(insertQuery, email));
        assertDoesNotThrow( () -> operations.execute(deleteQuery, email));
    }

    @DisplayName("Execute method but setting up a consumer")
    @Order(3)
    @Test
    void executeWithConsumer() {
        String email = "foo@bar.com";
        String insertQuery = String.format("INSERT INTO customers (first_name, last_name, email, password, " +
                "phone_number, address, categories_preferences) VALUES ('John', 'Doe', " +
                "?, 'password123', '555-0100', '123 Elm St, Springfield, " +
                "IL', ARRAY['Electronics', 'Books']);", email);
        String deleteQuery = "DELETE FROM customers WHERE email = ?";
        assertDoesNotThrow( () ->
            operations.execute(insertQuery, stmt -> {
                try {
                    stmt.setString(1, email);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            })
        );

        assertDoesNotThrow( () ->
            operations.execute(deleteQuery, stmt -> {
                try {
                    stmt.setString(1, email);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            })
        );

    }

    @DisplayName("Select with findOne method, setting up a mapper")
    @Order(4)
    @Test
    void findOneRecordFound() {
        String query = "SELECT * FROM customers WHERE email = ?";
        String email = "foo2@bar.com";

        String insertQuery = String.format("INSERT INTO customers (first_name, last_name, email, password, " +
                "phone_number, address, categories_preferences) VALUES ('John', 'Doe', " +
                "?, 'password123', '555-0100', '123 Elm St, Springfield, " +
                "IL', ARRAY['Electronics', 'Books']);", email);
        try {
            operations.execute(insertQuery, stmt -> {
                try {
                    stmt.setString(1, email);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            throw new DatabaseException(Error.INSERT_EXCEPTION.getDescription(), e.getCause());
        }

        try {
            Customer customer = operations.findOne(query, resultSet -> {
                try {
                    resultSet.next();

                    Array preferencesArray = resultSet.getArray("categories_preferences");
                    List<String> preferencesList = new ArrayList<>();

                    if (preferencesArray != null) {
                        Object[] arrayData = (Object[]) preferencesArray.getArray();
                        for (Object item : arrayData) {
                            if (item instanceof String) {
                                preferencesList.add((String) item);
                            }
                        }
                    }

                    return Customer.builder()
                            .id(Long.valueOf(resultSet.getString("pk_customer_id")))
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("password"))
                            .phoneNumber(resultSet.getString("phone_number"))
                            .address(resultSet.getString("address"))
                            .categoriesPreferences(preferencesList)
                            .build();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }, email);

            Log.info(customer.toString());

            assertNotNull(customer);
            assertEquals(email, customer.getEmail());

        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @DisplayName("Looking dor a not found exception")
    @Order(5)
    @Test
    void findOneRecordNotFound() {
        String query = "SELECT * FROM customers WHERE pk_customer_id = ?";
        int idToFindIncorrect = 99;

        assertThrows(EntityNotFound.class, () -> operations.findOne(query,
                resultSet -> Customer.builder().build(),
                idToFindIncorrect));

    }

    @DisplayName("Select with findMany method, setting up a mapper")
    @Order(6)
    @Test
    void findMany() {
        String query = "SELECT * FROM customers";
        List<Customer> customers;
        try {
            customers = operations.findMany(query, resultSet -> {
                try {
                    Array preferencesArray = resultSet.getArray("categories_preferences");
                    List<String> preferencesList = new ArrayList<>();

                    if (preferencesArray != null) {
                        Object[] arrayData = (Object[]) preferencesArray.getArray();
                        for (Object item : arrayData) {
                            if (item instanceof String) {
                                preferencesList.add((String) item);
                            }
                        }
                    }

                    return Customer.builder()
                            .id(Long.valueOf(resultSet.getString("pk_customer_id")))
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("password"))
                            .phoneNumber(resultSet.getString("phone_number"))
                            .address(resultSet.getString("address"))
                            .categoriesPreferences(preferencesList)
                            .build();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        customers.forEach(System.out::println);
        assertThat(customers).isNotNull();
    }

    @Disabled("This 'test helps us to clean all the database tables based on the profile settled")
    @DisplayName("Cleaning database tables")
    void tearDown() {
        StringBuilder query = new StringBuilder();

        if(activeProfile.equals("local")) {
            query.append("SET REFERENTIAL_INTEGRITY FALSE;" +
                    "TRUNCATE TABLE ORDERS;" +
                    "TRUNCATE TABLE CUSTOMERS;" +
                    "TRUNCATE TABLE PRODUCTS_ORDERS;"+
                    "SET REFERENTIAL_INTEGRITY TRUE;");
        }

        try {
            operations.execute(query.toString());
        } catch (SQLException e) {
            throw new DatabaseException(Error.DELETE_EXCEPTION.getDescription(), e.getCause());
        }
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