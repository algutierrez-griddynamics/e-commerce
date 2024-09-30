package org.ecommerce.util;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecommerce.models.Manager;
import org.ecommerce.models.Order;

public class JsonParser {
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static Order parseOrder(String json) throws JsonProcessingException {
        return MAPPER.readValue(json, Order.class);
    }

    public static Manager parseManager(String json) throws JsonProcessingException {
        return MAPPER.readValue(json, Manager.class);
    }
}
