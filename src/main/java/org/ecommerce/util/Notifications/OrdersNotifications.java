package org.ecommerce.util.Notifications;

import org.ecommerce.logs.Log;
import org.ecommerce.models.Order;

public class OrdersNotifications {

    public static void notify(Order order) {
        Log.info("Sending notification for order: " + order.getId());
    }
}
