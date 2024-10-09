package org.ecommerce.repositories;

import org.ecommerce.enums.OrderStatus;
import org.ecommerce.models.Order;
import org.ecommerce.repositories.impl.CrudOperationsImpl;
import org.ecommerce.util.database.Operations;

public class OrderRepository extends CrudOperationsImpl<Order> {

    private final Operations<Order> operations;

    OrderRepository(Operations<Order> operations) {
        this.operations = operations;
    }

    @Override
    public Order save(Order entity) {
        String sqlQuery = "INSERT INTO orders (fk_customer_id, fk_payment_details_id, fk_billing_information_id, fk_shipping_information_id" +
                "date, total_usd, status) VALUES (?, ?, ?, ?, ?, ?)";
        operations.execute(sqlQuery,
                entity.getCustomerId(), entity.getPaymentDetails(), entity.getBillingInformation(),
                entity.getShippingInformation(), entity.getOrderDate(), entity.getBillingInformation().getAmount()
                        + entity.getShippingInformation().getShippingCost(););

    }

    public void updateStatus(Order order, OrderStatus status) {
        order.setStatus(status);
    }
}
