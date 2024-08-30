package org.ecommerce.util.Notifications;

import org.ecommerce.logs.Log;
import org.ecommerce.models.Order;

public class OrdersNotifications extends Log {

    public static void notify(Order order) {
        Log.info("Sending notification for order: " + order);
    }
}