package org.ecommerce.util.database;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Data source properties for dev profile")
@ActiveProfiles("dev")
@SpringBootTest
class DevDataSourceConfigTest {

    @Autowired
    DataSourceProperties dataSourceProperties;

    @Test
    void getDevDataSource() {
        String url = dataSourceProperties.getUrl();

        assertTrue(url.contains("postgresql"));
    }
}