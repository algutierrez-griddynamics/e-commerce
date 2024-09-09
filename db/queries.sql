-- CREATE ORDERS
BEGIN;

-- Insert into `orders`
INSERT INTO orders (fk_shipping_information_id, fk_billing_information_id, fk_payment_details_id, fk_customer_id, date, status, total_usd) VALUES
                                                                                                                                               (1, 1, 1, 1, '2024-09-01', 'Completed', 19.99),
                                                                                                                                               (2, 2, 2, 2, '2024-09-02', 'Pending', 29.99),
                                                                                                                                               (3, 3, 3, 3, '2024-09-03', 'Shipped', 49.99),
                                                                                                                                               (4, 4, 4, 4, '2024-09-04', 'Delivered', 99.99),
                                                                                                                                               (5, 5, 5, 5, '2024-09-05', 'Cancelled', 9.99),
                                                                                                                                               (6, 6, 6, 6, '2024-09-06', 'Completed', 15.99),
                                                                                                                                               (7, 7, 7, 7, '2024-09-07', 'Pending', 39.99),
                                                                                                                                               (8, 8, 8, 8, '2024-09-08', 'Shipped', 59.99),
                                                                                                                                               (9, 9, 9, 9, '2024-09-09', 'Delivered', 14.99),
                                                                                                                                               (10, 10, 10, 10, '2024-09-10', 'Completed', 24.99);

-- Insert into `products_orders`
INSERT INTO products_orders (fk_product_id, fk_order_id) VALUES
                                                             (1, 1),
                                                             (2, 2),
                                                             (3, 3),
                                                             (4, 4),
                                                             (5, 5),
                                                             (6, 6),
                                                             (7, 7),
                                                             (8, 8),
                                                             (9, 9),
                                                             (10, 10);

COMMIT;

-- CREATE ORDERS
BEGIN;

-- Insert into `orders`
INSERT INTO orders (fk_shipping_information_id, fk_billing_information_id, fk_payment_details_id, fk_customer_id, date, status, total_usd) VALUES
                                                                                                                                               (1, 1, 1, 1, '2024-09-01', 'Completed', 19.99),
                                                                                                                                               (2, 2, 2, 2, '2024-09-02', 'Pending', 29.99),
                                                                                                                                               (3, 3, 3, 3, '2024-09-03', 'Shipped', 49.99),
                                                                                                                                               (4, 4, 4, 4, '2024-09-04', 'Delivered', 99.99),
                                                                                                                                               (5, 5, 5, 5, '2024-09-05', 'Cancelled', 9.99),
                                                                                                                                               (6, 6, 6, 6, '2024-09-06', 'Completed', 15.99),
                                                                                                                                               (7, 7, 7, 7, '2024-09-07', 'Pending', 39.99),
                                                                                                                                               (8, 8, 8, 8, '2024-09-08', 'Shipped', 59.99),
                                                                                                                                               (9, 9, 9, 9, '2024-09-09', 'Delivered', 14.99),
                                                                                                                                               (10, 10, 10, 10, '2024-09-10', 'Completed', 24.99);

-- Insert into `products_orders`
INSERT INTO products_orders (fk_product_id, fk_order_id) VALUES
                                                             (1, 1),
                                                             (2, 2),
                                                             (3, 3),
                                                             (4, 4),
                                                             (5, 5),
                                                             (6, 6),
                                                             (7, 7),
                                                             (8, 8),
                                                             (9, 9),
                                                             (10, 10);

COMMIT;

-- Read Orders
SELECT
    o.pk_order_id,
    c.pk_customer_id, c.first_name, c.last_name, c.email, c.phone_number,
    o.date as order_placement_date, o.status, o.total_usd,
    si.tracking_number, si.status, si.date as Shipping_Information_update_date,
    bi.date as Bill_Date,
    ai.country, ai.city, ai.state, ai.street, ai.zip_code,
    pd.card_holder_name, pd.card_number, pd.date as Card_Expiration_Date, pd.card_cvv, pd.payment_method_type,
    p.amount, p.currency_code, p.description
FROM orders o
         INNER JOIN shipping_information si on o.fk_shipping_information_id = si.pk_shipping_information_id
         INNER JOIN billing_information bi on o.fk_billing_information_id = bi.pk_billing_information_id
         INNER JOIN address_information ai on ai.pk_address_information_id = bi.fk_address_information_id
         INNER JOIN payment_details pd on o.fk_payment_details_id = pd.pk_payment_details_id
         INNER JOIN customers c on o.fk_customer_id = c.pk_customer_id
         INNER JOIN prices p on bi.fk_price_id = p.pk_price_id;

-- DELETE ORDERS
DELETE FROM orders
WHERE pk_order_id = 10;

-- Update Order Status
UPDATE orders
SET status = 'Cancelled'
WHERE pk_order_id = 1;

