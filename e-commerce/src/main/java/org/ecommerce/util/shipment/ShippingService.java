package org.ecommerce.util.shipment;

import org.ecommerce.enums.OrderStatus;
import org.ecommerce.logs.Log;
import org.ecommerce.models.ShippingInformation;

public class ShippingService {

    public static OrderStatus processShipment(ShippingInformation shippingInformation) {
        Log.info("Processing shipping information: " + shippingInformation);
        return OrderStatus.CONFIRMED;
    }

    public static OrderStatus getOrderStatus() {
        Log.info("Getting order status");
        return OrderStatus.SHIPPED;
    }
}
