package org.ecommerce.repositories.inmemory;

import org.ecommerce.enums.Error;
import org.ecommerce.enums.OrderStatus;
import org.ecommerce.exceptions.DatabaseException;
import org.ecommerce.models.*;
import org.ecommerce.repositories.inmemory.impl.CrudInMemoryOperationsImpl;
import org.ecommerce.util.database.Operations;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderRepository extends CrudInMemoryOperationsImpl<Order> {

    public OrderRepository(Operations<Order> operations) {
        super(operations);
    }

    @Override
    public Order save(Order entity) {
        String sqlQuery = "INSERT INTO orders (fk_customer_id, fk_payment_details_id, fk_billing_information_id, fk_shipping_information_id," +
                "date, total_usd, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            operations.execute(sqlQuery,
                    entity.getCustomer().getId(), entity.getPaymentDetails().getId(), entity.getBillingInformation().getId(),
                    entity.getShippingInformation().getId(), new Date(entity.getOrderDate().getTime()),
                    entity.getShippingInformation().getShippingCost().getAmount()
                            .add(entity.getBillingInformation().getAmount().getAmount()),
                    entity.getStatus().toString());
        } catch (SQLException e) {
            throw new DatabaseException(Error.INSERT_EXCEPTION.getDescription(), e);
        }
        return entity;
    }

    @Override
    public Optional<Order> findById(Long id) {
        String sqlQuery = "SELECT * FROM orders WHERE pk_order_id = ?";
        try {
            return Optional.of(
                    operations.findOne(sqlQuery, resultSet -> {
                        try {
                            resultSet.next();

                            return Order.builder()
                                    .id(Long.valueOf(resultSet.getString(1)))
                                    .shippingInformation(new ShippingInformation())
                                    .billingInformation(new BillingInformation())
                                    .paymentDetails(new PaymentDetails())
                                    .customer(new Customer())
                                    .orderDate(new java.util.Date(resultSet.getDate(6).getTime()))
                                    .status(OrderStatus.valueOf(resultSet.getString(7).toUpperCase()))
                                    .build();
                        } catch (SQLException e) {
                            throw new DatabaseException(e.toString(), e);
                        }

                    }, id)
            );
        } catch (SQLException e) {
            throw new DatabaseException(Error.SELECT_EXCEPTION.getDescription(), e);
        }
    }

    @Override
    public void update(Long id, Order entity) {
        String sqlQuery = "UPDATE orders SET fk_shipping_information_id = ?, fk_billing_information_id = ?" +
                ", fk_payment_details_id = ?, fk_customer_id = ?, date = ?, status = ? where pk_order_id = ?";
            try {
                operations.execute(sqlQuery, entity.getShippingInformation().getId(), entity.getBillingInformation().getId(),
                        entity.getPaymentDetails().getId(), entity.getCustomer().getId(), entity.getOrderDate(), entity.getStatus().toString(),
                        id);
            } catch (SQLException e) {
                throw new DatabaseException(Error.INSERT_EXCEPTION.getDescription(), e);
            }
    }

    @Override
    public void deleteById(Long id) {
        String sqlQuery = "DELETE FROM orders WHERE pk_order_id = ?";
        findById(id).ifPresentOrElse((order) -> {
            try {
                operations.execute(sqlQuery, id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }}, () -> {throw new DatabaseException(Error.DELETE_EXCEPTION.getDescription());}
        );
    }

    @Override
    public List<Order> findAll() {
        String sqlQuery = "SELECT * FROM orders";

        try {
            return operations.findMany(sqlQuery, resultSet -> {
                try {
                    return Order.builder()
                            .id(resultSet.getLong(1))
                            .shippingInformation(new ShippingInformation())
                            .billingInformation(new BillingInformation())
                            .paymentDetails(new PaymentDetails())
                            .customer(new Customer())
                            .orderDate(new java.util.Date(resultSet.getDate(6).getTime()))
                            .status(OrderStatus.valueOf(resultSet.getString(7).toUpperCase()))
                            .build();
                } catch (SQLException e) {
                    throw new DatabaseException(e.toString(), e);
                }
            });
        } catch (SQLException e) {
            throw new DatabaseException(Error.SELECT_EXCEPTION.getDescription(), e);
        }
    }

    public void updateStatus(Order order, OrderStatus status) {
        order.setStatus(status);
    }
}
