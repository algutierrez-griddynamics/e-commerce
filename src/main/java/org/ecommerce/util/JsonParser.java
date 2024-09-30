package org.ecommerce.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T parseJson(String json, Class<T> valueType) throws JsonProcessingException {
        return MAPPER.readValue(json, valueType);
    }
}
