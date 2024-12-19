-- Populating h2 database
-- Insert into PRICES
INSERT INTO PRICES (PK_PRICE_ID, AMOUNT, CURRENCY_CODE)
VALUES
    (1001, 29.99, 'MXN'),
    (1002, 49.99, 'GBP');

-- Insert into SHIPPING_INFORMATION
INSERT INTO SHIPPING_INFORMATION (PK_SHIPPING_INFORMATION_ID, ESTIMATED_DELIVERY_DATE, FK_PRICE_ID, TRACKING_NUMBER, CITY, COUNTRY, STATE, STREET, ZIP_CODE, SHIPPING_STATUS)
VALUES
    (1, '2024-10-20 15:00:00', 1001, 123456789, 'New York', 'USA', 'NY', '123 Broadway', '10001', 'SHIPPED'),
    (2, '2024-10-21 12:00:00', 1002, 987654321, 'Los Angeles', 'USA', 'CA', '456 Sunset Blvd', '90001', 'REQUESTED');

-- Insert into LOCATIONS
INSERT INTO LOCATIONS (PK_LOCATION_ID, DEPTH_CM, HEIGHT_CM, WIDTH_CM, CITY, COUNTRY, STATE, STREET, ZIP_CODE)
VALUES
    (1, 100, 200, 300, 'Chicago', 'USA', 'IL', '789 Lake St', '60601'),
    (2, 150, 250, 350, 'Miami', 'USA', 'FL', '321 Ocean Dr', '33101');

-- Insert into PAYMENT_DETAILS
INSERT INTO PAYMENT_DETAILS (PK_PAYMENT_DETAILS_ID, BILLING_DATE, CARD_EXPIRATION_DATE, CARD_HOLDER_NAME, CARD_NUMBER, CARDCVV, PAYMENT_METHOD_TYPE)
VALUES
    (1, '2024-10-15 10:00:00', '2025-12-01', 'John Doe', '4111111111111111', '123', 'CREDIT_CARD'),
    (2, '2024-10-16 09:00:00', '2024-11-30', 'Jane Smith', '5500000000000004', '456', 'DEBIT_CARD');

-- -- Insert into CUSTOMER_PREFERENCES
-- INSERT INTO CUSTOMER_PREFERENCES (CUSTOMER_ID, CATEGORIES_PREFERENCES)
-- VALUES
--     (1, 'Electronics'),
--     (2, 'Fashion');

-- Insert into PRODUCTS
INSERT INTO PRODUCTS (PK_PRODUCT_ID, INVENTORY_ID, DESCRIPTION, NAME, FK_PRICE_ID)
VALUES
    (2001, 3001, 'Smart Phone', 'Galaxy S21', 1001),
    (2002, 3002, 'Stylish Jacket', 'Winter Coat', 1002);


-- Insert into STOCK_ENTRY
INSERT INTO STOCK_ENTRY (STOCK, FK_LOCATION_ID, FK_PRODUCT_ID, MEASUREMENT_UNIT)
VALUES
    (100, 1, 2001, 'Units'),
    (200, 2, 2002, 'Units');

-- Insert into ADMINS
INSERT INTO ADMINS (PK_ADMIN_ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES
    (1, 'admin@example.com', 'Alice', 'Smith', 'adminpass'),
    (2, 'admin2@example.com', 'Bob', 'Johnson', 'adminpass');

-- Insert into CATEGORIES
INSERT INTO CATEGORIES (PK_CATEGORY_ID, DESCRIPTION, NAME, CATEGORY_TYPE)
VALUES
    (1, 'Electronics', 'Gadgets', 'CHILDREN'),
    (2, 'Clothing', 'Apparel', 'PARENT');

-- Insert into CUSTOMERS
INSERT INTO CUSTOMERS (ADDRESS, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, CATEGORIES_PREFERENCES)
VALUES
    ('10 Main St', 'customer1@example.com', 'John', 'Doe', 'pass123', '123-456-7890', '{Electronics, Fashion}'),
    ('20 Market St', 'customer2@example.com', 'Jane', 'Smith', 'pass456', '987-654-3210', '{Books, Home Decor}');

-- Insert into BILLING_INFORMATION
INSERT INTO BILLING_INFORMATION (PK_BILLING_INFORMATION_ID, BILLING_DATE, ORDER_ID, FK_PRICE_ID, CITY, COUNTRY, STATE, STREET, ZIP_CODE)
VALUES
    (1, '2024-10-15T10:00:00', 1, 1001, 'New York', 'USA', 'NY', '123 Broadway', '10001'),
    (2, '2024-10-16T09:00:00', 2, 1002, 'Los Angeles', 'USA', 'CA', '456 Sunset Blvd', '90001');

-- Insert into ORDERS
INSERT INTO ORDERS (TOTAL_USD, DATE, FK_BILLING_INFORMATION_ID, FK_CUSTOMER_ID, FK_PAYMENT_DETAILS_ID, FK_SHIPPING_INFORMATION_ID, STATUS)
VALUES
    (79.98, '2024-10-15 10:00:00', 1, 1, 1, 1, 'CONFIRMED'),
    (49.99, '2024-10-16 09:00:00', 2, 2, 2, 2, 'SHIPPED');

INSERT INTO employees (first_name, last_name, email, password, employee_number, role)
VALUES
    ('John', 'Doe', 'john.doe@example.com', '{bcrypt}$2y$10$UV8FU61mtiPvAXAt2nij3.wkmxLHvjy8TNiZsf.mcd6vz54mnErzm', 101, 'Manager'),
    ('Jane', 'Smith', 'jane.smith@example.com', '{bcrypt}$2y$10$HgeWcZZ/rSea9zBv8/kle.Wpia5lg/54md8H3ocLAnfwGBc4BdcPm', 102, 'Manager'),
    ('Bob', 'Johnson', 'bob.johnson@example.com', '{noop}password789', 103, 'Cashier'),
    ('Alice', 'Williams', 'alice.williams@example.com', '{noop}password012', 104, 'Cashier'),
    ('Mike', 'Brown', 'mike.brown@example.com', '{noop}password345', 105, 'Salesperson');