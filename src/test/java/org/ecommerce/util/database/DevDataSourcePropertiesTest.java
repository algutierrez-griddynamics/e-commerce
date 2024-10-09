package org.ecommerce.util.database;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Data source properties for dev profile")
@ActiveProfiles("dev")
@SpringBootTest
class DevDataSourcePropertiesTest {

    @Autowired
    DataSourceProperties dataSourceProperties;

    @DisplayName("Successful postgres connection")
    @Test
    void getDevDataSource() {
        String url = dataSourceProperties.getUrl();

        assertTrue(url.contains("postgresql"));
    }
 }