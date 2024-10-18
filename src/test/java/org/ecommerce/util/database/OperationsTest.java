package org.ecommerce.util.database;

import com.zaxxer.hikari.HikariDataSource;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.logs.Log;
import org.ecommerce.models.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Tests for Operations class")
//@Import("some.other.config.bean.files")
class OperationsTest {
    @TestConfiguration
    static class operationsConfig {
        @Bean
        Operations<Customer> operations(HikariDataSource hikariDataSource) {
            return new Operations<>(hikariDataSource);
        }
    }

    @Autowired
    private Operations<Customer> operations;

    @DisplayName("Checking database connection")
    @Test
    void getConnection() {
        try (Connection connection = operations.getConnection()) {
            assertNotNull(connection);
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @DisplayName("Inserting with execute method")
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
    @Test
    void findOneRecordFound() {
        String query = "SELECT * FROM customers WHERE pk_customer_id = ?";
        int idToFindCorrect = 1;

        try {
            Customer customer = operations.findOne(query, resultSet -> {
                try {
                    resultSet.next();

                    String[] preferencesArray = (String[]) resultSet.getArray(8).getArray();
                    List<String> preferencesList = Stream.of(preferencesArray)
                            .collect(Collectors.toList());

                    return Customer.builder()
                            .id(Long.valueOf(resultSet.getString(1)))
                            .firstName(resultSet.getString(2))
                            .lastName(resultSet.getString(3))
                            .email(resultSet.getString(4))
                            .password(resultSet.getString(5))
                            .phoneNumber(resultSet.getString(6))
                            .address(resultSet.getString(7))
                            .preferences(preferencesList)
                            .build();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }, idToFindCorrect);

            Log.info(customer.toString());

            assertNotNull(customer);
            assertEquals(idToFindCorrect, customer.getId());

        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @DisplayName("Looking dor a not found exception")
    @Test
    void findOneRecordNotFound() {
        String query = "SELECT * FROM customers WHERE pk_customer_id = ?";
        int idToFindIncorrect = 99;

        assertThrows(EntityNotFound.class, () -> operations.findOne(query,
                resultSet -> Customer.builder().build(),
                idToFindIncorrect));

    }

    @DisplayName("Select with findMany method, setting up a mapper")
    @Test
    void findMany() {
        String query = "SELECT * FROM customers";
        List<Customer> customers;
        try {
            customers = operations.findMany(query, resultSet -> {
                try {
                    String[] preferencesArray = (String[]) resultSet.getArray(8).getArray();
                    List<String> preferencesList = Stream.of(preferencesArray)
                            .collect(Collectors.toList());

                    return Customer.builder()
                            .id(Long.valueOf(resultSet.getString(1)))
                            .firstName(resultSet.getString(2))
                            .lastName(resultSet.getString(3))
                            .email(resultSet.getString(4))
                            .password(resultSet.getString(5))
                            .phoneNumber(resultSet.getString(6))
                            .address(resultSet.getString(7))
                            .preferences(preferencesList)
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
}