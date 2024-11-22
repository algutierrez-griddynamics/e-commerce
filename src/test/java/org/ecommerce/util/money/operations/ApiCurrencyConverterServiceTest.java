package org.ecommerce.util.money.operations;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApiCurrencyConverterServiceTest {

    @Autowired
    private ApiCurrencyConverterService service;

    @Test
    void fetchData() throws JsonProcessingException {
       Map<String, Double> response = service.fetchCurrenciesMapFromApi();

       assertNotNull(response);
       assertFalse(response.isEmpty());
    }
}