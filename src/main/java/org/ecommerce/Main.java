package org.ecommerce;

import org.ecommerce.controllers.OrderController;
import org.ecommerce.logs.Log;
import org.ecommerce.message.broker.MessageQueue;
import org.ecommerce.repositories.OrderRepository;
import org.ecommerce.services.impl.OrderServiceImpl;

public class Main {
    public static void main (String[] args) {
//        placeOrders();
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
    }

    private static void placeOrders() {
        OrderController orderController = new OrderController(
                new OrderServiceImpl(new OrderRepository()
                        , new MessageQueue<>()));

        String request = "{\n" +
                "  \"id\": 1,\n" +
                "  \"customerId\": 12345,\n" +
                "  \"orderDate\": \"2024-09-01\",\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"inventoryId\": 56789,\n" +
                "      \"name\": \"Wireless Mouse\",\n" +
                "      \"price\": {\n" +
                "        \"currencyCode\": \"USD\",\n" +
                "        \"amount\": 29.99\n" +
                "      },\n" +
                "      \"description\": \"Ergonomic wireless mouse\",\n" +
                "      \"categories\": [\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"name\": \"Electronics\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 2,\n" +
                "          \"name\": \"Accessories\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"inventoryId\": 67890,\n" +
                "      \"name\": \"Mechanical Keyboard\",\n" +
                "      \"price\": {\n" +
                "        \"currencyCode\": \"USD\",\n" +
                "        \"amount\": 89.99\n" +
                "      },\n" +
                "      \"description\": \"Mechanical keyboard with RGB lighting\",\n" +
                "      \"categories\": [\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"name\": \"Electronics\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 3,\n" +
                "          \"name\": \"Keyboards\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"PLACED\",\n" +
                "  \"shippingInformation\": {\n" +
                "    \"id\": 1,\n" +
                "    \"trackingNumber\": 987654321,\n" +
                "    \"shippingStatus\": \"PLACED\",\n" +
                "    \"shippingCost\": {\n" +
                "      \"currencyCode\": \"USD\",\n" +
                "      \"amount\": 15.00\n" +
                "    },\n" +
                "    \"estimatedDeliveryDate\": \"2024-09-05\",\n" +
                "    \"street\": \"123 Main St\",\n" +
                "    \"city\": \"New York\",\n" +
                "    \"state\": \"NY\",\n" +
                "    \"zipCode\": \"10001\",\n" +
                "    \"country\": \"USA\"\n" +
                "  },\n" +
                "  \"billingInformation\": {\n" +
                "    \"id\": 1,\n" +
                "    \"orderId\": 1,\n" +
                "    \"billingDate\": \"2024-09-01\",\n" +
                "    \"amount\": {\n" +
                "      \"currencyCode\": \"USD\",\n" +
                "      \"amount\": 134.98\n" +
                "    },\n" +
                "    \"street\": \"123 Main St\",\n" +
                "    \"city\": \"New York\",\n" +
                "    \"state\": \"NY\",\n" +
                "    \"zipCode\": \"10001\",\n" +
                "    \"country\": \"USA\"\n" +
                "  },\n" +
                "  \"paymentDetails\": {\n" +
                "    \"id\": 1,\n" +
                "    \"paymentMethodType\": \"CREDIT_CARD\",\n" +
                "    \"cardNumber\": \"4111111111111111\",\n" +
                "    \"cardExpirationDate\": \"2025-10-01\",\n" +
                "    \"cardHolderName\": \"John Doe\"\n" +
                "  }\n" +
                "}\n";
        orderController.consumeOrders();

        Log.info(orderController.create(request).toString());
        Log.info(orderController.create(request).toString());
        Log.info(orderController.create(request).toString());

    }
}
