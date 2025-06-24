-- CREATE ORDERS
SELECT 'Create new orders, by inserting in the orders table as well as the products_orders as a transaction';
BEGIN;

-- Insert into `orders`
INSERT INTO orders (fk_shipping_information_id, fk_billing_information_id, fk_payment_details_id, fk_customer_id, date, status, total_usd) VALUES
                                                                                                                                               (1, 1, 1, 1, '2024-09-01', 'IN_TRANSIT', 19.99),
                                                                                                                                               (2, 2, 2, 2, '2024-09-02', 'PLACED', 29.99),
                                                                                                                                               (3, 3, 3, 3, '2024-09-03', 'PLACED', 49.99),
                                                                                                                                               (4, 4, 4, 4, '2024-09-04', 'Delivered', 99.99),
                                                                                                                                               (5, 5, 5, 5, '2024-09-05', 'Cancelled', 9.99),
                                                                                                                                               (6, 6, 6, 6, '2024-09-06', 'IN_TRANSIT', 15.99),
                                                                                                                                               (7, 7, 7, 7, '2024-09-07', 'PLACED', 39.99),
                                                                                                                                               (8, 8, 8, 8, '2024-09-08', 'Shipped', 59.99),
                                                                                                                                               (9, 9, 9, 9, '2024-09-09', 'Delivered', 14.99),
                                                                                                                                               (10, 10, 10, 10, '2024-09-10', 'IN_TRANSIT', 24.99);

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
SELECT 'Read Orders and their information with other tables, using joins';
SELECT
    o.pk_order_id,
    c.pk_customer_id, c.first_name, c.last_name, c.email, c.phone_number,
    o.date as order_placement_date, o.status, o.total_usd,
    si.tracking_number, si.status, si.date as Shipping_Information_update_date,
    bi.date as Bill_Date,
    ai.country, ai.city, ai.state, ai.street, ai.zip_code,
    pd.card_holder_name, pd.card_number, pd.date as Card_Expiration_Date, pd.payment_method_type,
    p.amount, p.currency_code, p.description
FROM orders o
         INNER JOIN shipping_information si on o.fk_shipping_information_id = si.pk_shipping_information_id
         INNER JOIN billing_information bi on o.fk_billing_information_id = bi.pk_billing_information_id
         INNER JOIN address_information ai on ai.pk_address_information_id = bi.fk_address_information_id
         INNER JOIN payment_details pd on o.fk_payment_details_id = pd.pk_payment_details_id
         INNER JOIN customers c on o.fk_customer_id = c.pk_customer_id
         INNER JOIN prices p on bi.fk_price_id = p.pk_price_id;

-- DELETE ORDERS
SELECT 'Deleting Order with id=10';
SELECT pk_order_id from orders where pk_order_id = 10;

DELETE FROM orders
WHERE pk_order_id = 10;

SELECT pk_order_id from orders where pk_order_id = 10;

-- Update Order Status
SELECT 'Update Order Status to canceled where id = 1. Current Status';
SELECT status FROM orders WHERE pk_order_id = 1;

UPDATE orders
SET status = 'Cancelled'
WHERE pk_order_id = 1;

SELECT 'New Status';
SELECT status FROM orders WHERE pk_order_id = 1;

-- Read Orders with filters, based on name and in between two date ranges. adding dynamic pagination and sorting
SELECT 'Reading Orders with filters, based on the name and in between two date ranges that corresponds to the order placement date. Also adding pagination and sorting. Harcoded values';
SELECT
    o.pk_order_id,
    c.pk_customer_id, c.first_name, c.last_name, c.email, c.phone_number,
    o.date as order_placement_date, o.status, o.total_usd,
    si.tracking_number, si.status, si.date as Shipping_Information_update_date,
    bi.date as Bill_Date,
    ai.country, ai.city, ai.state, ai.street, ai.zip_code,
    pd.card_holder_name, pd.card_number, pd.date as Card_Expiration_Date, pd.payment_method_type,
    p.amount, p.currency_code, p.description
FROM orders o
         INNER JOIN shipping_information si on o.fk_shipping_information_id = si.pk_shipping_information_id
         INNER JOIN billing_information bi on o.fk_billing_information_id = bi.pk_billing_information_id
         INNER JOIN address_information ai on ai.pk_address_information_id = bi.fk_address_information_id
         INNER JOIN payment_details pd on o.fk_payment_details_id = pd.pk_payment_details_id
         INNER JOIN customers c on o.fk_customer_id = c.pk_customer_id
         INNER JOIN prices p on bi.fk_price_id = p.pk_price_id
WHERE
    (c.first_name ILIKE '%' || 'john' || '%' AND c.last_name ILIKE  '%' || 'doe' || '%')
  --(c.first_name ILIKE '%' || :CUSTOMER_FIRST_NAME || '%' AND c.last_name ILIKE  '%' || :CUSTOMER_LAST_NAME || '%')
  AND
    (o.date BETWEEN '2024-08-01' and '2024-12-01')
--(o.date BETWEEN :START_ORDER_PLACEMENT_DATE and :END_ORDER_PLACEMENT_DATE)
ORDER BY
    CASE WHEN 'name' = 'name' THEN c.first_name END
    --     CASE WHEN :sort_by = 'name' THEN c.first_name END,
    --     CASE WHEN :sort_by = 'total' THEN o.total_usd END
    LIMIT 10 OFFSET 0;
-- LIMIT :limit OFFSET :offset;

-- Statistics | number of orders a customer has placed
SELECT 'Statistics | number of orders a customer has placed';
SELECT COUNT(*)
FROM orders o
         JOIN customers ON o.fk_customer_id = customers.pk_customer_id
WHERE o.fk_customer_id = 1;
-- WHERE o.fk_customer_id = :TARGET_ID

-- Top something | Most purchased product
SELECT 'Top something | Most purchased product';
SELECT * FROM products
WHERE pk_product_id = (SELECT po.fk_product_id
                       FROM products_orders po
                                JOIN products pr ON po.fk_product_id = pr.pk_product_id
                       GROUP BY po.fk_product_id
                       ORDER BY COUNT(*) DESC LIMIT 1);