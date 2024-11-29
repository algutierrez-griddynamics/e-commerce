package org.ecommerce.util.money.operations;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class ApiCurrencyConverterServiceTest {

    @Autowired
    private ApiCurrencyConverterService service;

    @Disabled("Since the number of API calls is settled as 100 per month," +
            "this test has been disabled to use the 100 calls in specific scenarios")
    @Test
    void fetchData() throws JsonProcessingException {
       Map<String, Double> response = service.fetchCurrenciesMapFromApi();

       assertNotNull(response);
       assertFalse(response.isEmpty());
    }
}