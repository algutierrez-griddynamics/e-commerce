package org.ecommerce.util.money.operations;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ecommerce.util.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ApiCurrencyConverterService {

    private RestTemplate restTemplate;

    public void ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.currency.converter.url}")
    private String API_URL;

    @Value("${api.currency.converter.key}")
    private String API_KEY;

    public ApiCurrencyConverterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Double> fetchCurrenciesMapFromApi() throws JsonProcessingException {
        final String URL_WITH_PARAMETERS = API_URL + "?access_key=" + API_KEY;

        return JsonParser.parseJson(
                restTemplate.getForObject(URL_WITH_PARAMETERS, String.class),
                ApiCurrencyConverterResponse.class
        ).getRates();
    }
}

