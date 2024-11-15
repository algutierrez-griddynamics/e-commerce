package org.ecommerce.util.money.operations;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ecommerce.util.JsonParser;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class UsdConverter {

    private static ApiCurrencyConverterService apiCurrencyConverterService;

    UsdConverter(ApiCurrencyConverterService apiCurrencyConverterService) {
        UsdConverter.apiCurrencyConverterService = apiCurrencyConverterService;
    }

    /**
     * @param from String Currency code of the source
     * @param to String Currency code of the expected output
     * @param amount BigDecimal amount that will be converted
     * @return The converted equivalence of the amount from source currency to expected currency
     * @throws JsonProcessingException If {@link #apiCurrencyConverterService} fails while parsing
     * the response to {@link ApiCurrencyConverterResponse} inside of {@link JsonParser}
     */

    public static BigDecimal convertAmountFromTo(String from, String to, BigDecimal amount) throws JsonProcessingException {
        final String EUR_CODE = "EUR";

        Map<String, Double> currenciesMap = UsdConverter.apiCurrencyConverterService.fetchCurrenciesMapFromApi();

        double amountInEuros = (amount.doubleValue() * currenciesMap.get(EUR_CODE)) / currenciesMap.get(from);
        double amountInTo = amountInEuros * currenciesMap.get(to);

        return new BigDecimal(amountInTo);
    }

}
