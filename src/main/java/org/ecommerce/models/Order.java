package org.ecommerce.models;

import java.sql.Date;
import java.util.Currency;
import java.util.List;

public class Order extends Identity {
    private Long customerId;
    private Date orderDate;
    private String status;
    private Currency totalAmount;
    private String shippingAddress;
    private List<Product> products;

    Order(Long id, Long customerId, Date orderDate, String status, Currency totalAmount, String shippingAddress, List<Product> products) {
        super(id);
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.shippingAddress = shippingAddress;
        this.products = products;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Currency getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Currency totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
