package org.ecommerce.message.broker.consumers;

import org.ecommerce.logs.Log;
import org.ecommerce.message.broker.MessageQueue;
import org.ecommerce.models.Order;
import org.ecommerce.models.Product;
import org.ecommerce.util.Notifications.OrdersNotifications;

import java.util.List;

public class OrderConsumer extends Consumer <Order> {
    public OrderConsumer(MessageQueue<Order> queue) {
        super(queue);
    }

    @Override
    void processOrder(Order order) {
        Log.info("Processing order: " + order);
        List<Product> availableProducts = order.getProducts(); // Iterate all products to check availability


        chargeCustomer(order);
        sendNotification(order);
    }

    @Override
    int checkForInventory(Product order) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    void updateInventory(Product order) {
        Log.info("Updating inventory for order: " + order);
    }

    @Override
    void chargeCustomer(Order order) {
        Log.info("Charging customer for order: " + order);
    }

    @Override
    void sendNotification(Order order) {
        OrdersNotifications.notify(order);
    }
}
