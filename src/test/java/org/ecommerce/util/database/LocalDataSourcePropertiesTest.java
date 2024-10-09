package org.ecommerce.util.database;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Data Source properties for local h2 database")
@ActiveProfiles("local")
@SpringBootTest
class LocalDataSourcePropertiesTest {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @DisplayName("Successful h2 connection")
    @Test
    void getLocalDataSource() {
        String url = dataSourceProperties.getUrl();

        assertTrue(url.contains("h2"));
    }
}