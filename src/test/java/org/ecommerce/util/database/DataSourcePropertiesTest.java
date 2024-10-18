package org.ecommerce.util.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("dev") // -> Profile settled as dev
class DataSourcePropertiesTest {

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        // Overriding properties at runtime
        registry.add("gd.datasource.url", () -> "jdbc:h2:mem:localdb");
        registry.add("gd.datasource.username", () -> "sa");
        registry.add("gd.datasource.password", () -> "password");
    }

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private Environment environment;

    @Test
    void getOverrideProperties() {
        // Current profile is still dev
        assertTrue(Arrays.asList(environment.getActiveProfiles()).contains("dev"));

        // Even though, properties have been overridden at runtime with the @DynamicPropertySource
        assertEquals("jdbc:h2:mem:localdb", dataSourceProperties.getUrl());
    }
}