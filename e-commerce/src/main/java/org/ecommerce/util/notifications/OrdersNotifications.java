package org.ecommerce.util.notifications;

import org.ecommerce.logs.Log;
import org.ecommerce.models.Order;

public class OrdersNotifications {

    public static void notify(Order order) {
        Log.info("Sending notification for order: " + order.getId());
    }
}
