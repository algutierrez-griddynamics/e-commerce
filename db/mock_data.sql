-- Insert data into `customers`
INSERT INTO customers (first_name, last_name, email, password, phone_number, address, categories_preferences) VALUES
                                                                                                                  ('John', 'Doe', 'john.doe@example.com', 'password123', '555-0100', '123 Elm St, Springfield, IL', ARRAY['Electronics', 'Books']),
                                                                                                                  ('Jane', 'Smith', 'jane.smith@example.com', 'password456', '555-0101', '456 Oak St, Springfield, IL', ARRAY['Clothing', 'Toys']),
                                                                                                                  ('Alice', 'Johnson', 'alice.johnson@example.com', 'password789', '555-0102', '789 Pine St, Springfield, IL', ARRAY['Home & Kitchen']),
                                                                                                                  ('Bob', 'Brown', 'bob.brown@example.com', 'password101', '555-0103', '101 Maple St, Springfield, IL', ARRAY['Sports', 'Automotive']),
                                                                                                                  ('Charlie', 'Williams', 'charlie.williams@example.com', 'password202', '555-0104', '202 Birch St, Springfield, IL', ARRAY['Books', 'Music']),
                                                                                                                  ('Diana', 'Jones', 'diana.jones@example.com', 'password303', '555-0105', '303 Cedar St, Springfield, IL', ARRAY['Electronics']),
                                                                                                                  ('Edward', 'Miller', 'edward.miller@example.com', 'password404', '555-0106', '404 Walnut St, Springfield, IL', ARRAY['Clothing', 'Electronics']),
                                                                                                                  ('Fiona', 'Davis', 'fiona.davis@example.com', 'password505', '555-0107', '505 Ash St, Springfield, IL', ARRAY['Toys', 'Home & Kitchen']),
                                                                                                                  ('George', 'Martinez', 'george.martinez@example.com', 'password606', '555-0108', '606 Elm St, Springfield, IL', ARRAY['Automotive', 'Books']),
                                                                                                                  ('Hannah', 'Garcia', 'hannah.garcia@example.com', 'password707', '555-0109', '707 Oak St, Springfield, IL', ARRAY['Music', 'Sports']);

-- Insert data into `prices`
INSERT INTO prices (currency_code, amount, description) VALUES
                                                            ('USD', 19.99, 'Basic Widget'),
                                                            ('USD', 29.99, 'Advanced Widget'),
                                                            ('USD', 49.99, 'Deluxe Widget'),
                                                            ('USD', 99.99, 'Premium Widget'),
                                                            ('USD', 9.99, 'Basic Accessory'),
                                                            ('USD', 15.99, 'Advanced Accessory'),
                                                            ('USD', 39.99, 'Deluxe Accessory'),
                                                            ('USD', 59.99, 'Premium Accessory'),
                                                            ('USD', 14.99, 'Simple Gadget'),
                                                            ('USD', 24.99, 'Complex Gadget');

-- Insert data into `prices`
INSERT INTO prices (currency_code, amount, description) VALUES
                                                                  ('USD', 5.99, 'Standard Shipping'),
                                                                  ('USD', 9.99, 'Expedited Shipping'),
                                                                  ('USD', 15.99, 'Two-Day Shipping'),
                                                                  ('USD', 24.99, 'Overnight Shipping'),
                                                                  ('USD', 4.99, 'Economy Shipping'),
                                                                  ('USD', 7.99, 'Express Shipping'),
                                                                  ('USD', 12.99, 'Priority Shipping'),
                                                                  ('USD', 19.99, 'Same-Day Shipping'),
                                                                  ('USD', 6.99, 'Standard International Shipping'),
                                                                  ('USD', 14.99, 'Expedited International Shipping');

-- Insert data into `categories`
INSERT INTO categories (name, description, type) VALUES
                                                     ('Electronics', 'Devices and gadgets', 'Consumer Goods'),
                                                     ('Books', 'Printed and digital books', 'Media'),
                                                     ('Clothing', 'Apparel and accessories', 'Fashion'),
                                                     ('Toys', 'Children’s toys and games', 'Entertainment'),
                                                     ('Home & Kitchen', 'Home appliances and kitchenware', 'Home Goods'),
                                                     ('Sports', 'Sporting goods and equipment', 'Recreation'),
                                                     ('Automotive', 'Car parts and accessories', 'Automotive'),
                                                     ('Music', 'Music instruments and accessories', 'Entertainment'),
                                                     ('Health', 'Health and wellness products', 'Personal Care'),
                                                     ('Office Supplies', 'Office equipment and supplies', 'Office');

-- Insert data into `inventories`
INSERT INTO inventories (sku) VALUES
                                  ('SKU12345'),
                                  ('SKU12346'),
                                  ('SKU12347'),
                                  ('SKU12348'),
                                  ('SKU12349'),
                                  ('SKU12350'),
                                  ('SKU12351'),
                                  ('SKU12352'),
                                  ('SKU12353'),
                                  ('SKU12354');

-- Insert data into `products`
INSERT INTO products (fk_inventory_id, fk_price_id, name, description) VALUES
                                                                           (1, 1, 'Basic Widget', 'A basic widget with essential features'),
                                                                           (2, 2, 'Advanced Widget', 'An advanced widget with additional features'),
                                                                           (3, 3, 'Deluxe Widget', 'A deluxe widget with premium features'),
                                                                           (4, 4, 'Premium Widget', 'The top-tier widget with all features'),
                                                                           (5, 5, 'Basic Accessory', 'An accessory for basic widgets'),
                                                                           (6, 6, 'Advanced Accessory', 'An accessory for advanced widgets'),
                                                                           (7, 7, 'Deluxe Accessory', 'An accessory for deluxe widgets'),
                                                                           (8, 8, 'Premium Accessory', 'An accessory for premium widgets'),
                                                                           (9, 9, 'Simple Gadget', 'A simple gadget with minimal features'),
                                                                           (10, 10, 'Complex Gadget', 'A complex gadget with multiple functions');

-- Insert data into `products_categories`
INSERT INTO products_categories (fk_product_id, fk_category_id) VALUES
                                                                    (1, 1),
                                                                    (2, 1),
                                                                    (3, 1),
                                                                    (4, 1),
                                                                    (5, 2),
                                                                    (6, 2),
                                                                    (7, 2),
                                                                    (8, 2),
                                                                    (9, 3),
                                                                    (10, 3);

-- Insert data into `address_information`
INSERT INTO address_information (street, city, state, zip_code, country) VALUES
                                                                             ('123 Elm St', 'Springfield', 'IL', '62701', 'USA'),
                                                                             ('456 Oak St', 'Springfield', 'IL', '62702', 'USA'),
                                                                             ('789 Pine St', 'Springfield', 'IL', '62703', 'USA'),
                                                                             ('101 Maple St', 'Springfield', 'IL', '62704', 'USA'),
                                                                             ('202 Birch St', 'Springfield', 'IL', '62705', 'USA'),
                                                                             ('303 Cedar St', 'Springfield', 'IL', '62706', 'USA'),
                                                                             ('404 Walnut St', 'Springfield', 'IL', '62707', 'USA'),
                                                                             ('505 Ash St', 'Springfield', 'IL', '62708', 'USA'),
                                                                             ('606 Elm St', 'Springfield', 'IL', '62709', 'USA'),
                                                                             ('707 Oak St', 'Springfield', 'IL', '62710', 'USA');

INSERT INTO locations (fk_address_id, width_cm, height_cm, depth_cm) VALUES
                                                                         (9, 100000, 8, 500),
                                                                         (10, 5000, 10, 8000);

-- Insert data into `storage_centers`
INSERT INTO stock_entry (fk_location_id, fk_inventory_id, measurement_unit, quantity) VALUES
                                                                              (1, 1, 'pieces', 100),
                                                                              (2, 2, 'pieces', 150),
                                                                              (1, 3, 'pieces', 200),
                                                                              (1, 4, 'pieces', 250),
                                                                              (2, 5, 'pieces', 300),
                                                                              (2, 6, 'pieces', 350),
                                                                              (2, 7, 'pieces', 400),
                                                                              (2, 8, 'pieces', 450),
                                                                              (1, 9, 'pieces', 500),
                                                                              (1, 10, 'pieces', 550);


-- Insert data into `shipping_information`
INSERT INTO shipping_information (fk_address_information_id, fk_price_id, tracking_number, date, status) VALUES
                                                                                                                    (1, 1, 'TRACK1234567890', '2024-09-01', 'Shipped'),
                                                                                                                    (2, 2, 'TRACK1234567891', '2024-09-02', 'In Transit'),
                                                                                                                    (3, 3, 'TRACK1234567892', '2024-09-03', 'Delivered'),
                                                                                                                    (4, 4, 'TRACK1234567893', '2024-09-04', 'Shipped'),
                                                                                                                    (5, 5, 'TRACK1234567894', '2024-09-05', 'In Transit'),
                                                                                                                    (6, 6, 'TRACK1234567895', '2024-09-06', 'Delivered'),
                                                                                                                    (7, 7, 'TRACK1234567896', '2024-09-07', 'Shipped'),
                                                                                                                    (8, 8, 'TRACK1234567897', '2024-09-08', 'In Transit'),
                                                                                                                    (9, 9, 'TRACK1234567898', '2024-09-09', 'Delivered'),
                                                                                                                    (10, 10, 'TRACK1234567899', '2024-09-10', 'Shipped');

-- Insert data into `billing_information`
INSERT INTO billing_information (fk_address_information_id, fk_price_id, date) VALUES
                                                                                   (1, 1, '2024-09-01'),
                                                                                   (2, 2, '2024-09-02'),
                                                                                   (3, 3, '2024-09-03'),
                                                                                   (4, 4, '2024-09-04'),
                                                                                   (5, 5, '2024-09-05'),
                                                                                   (6, 6, '2024-09-06'),
                                                                                   (7, 7, '2024-09-07'),
                                                                                   (8, 8, '2024-09-08'),
                                                                                   (9, 9, '2024-09-09'),
                                                                                   (10, 10, '2024-09-10');

-- Insert data into `payment_details`
INSERT INTO payment_details (payment_method_type, card_number, card_expiration_date, card_holder_name, card_cvv, date) VALUES
                                                                                                                                        ('Credit Card', '4111111111111111', '2026-12-31', 'John Doe', '123', '2024-09-01'),
                                                                                                                                        ('Debit Card', '4222222222222222', '2026-11-30', 'Jane Smith', '456', '2024-09-02'),
                                                                                                                                        ('Credit Card', '4333333333333333', '2025-10-31', 'Alice Johnson', '789', '2024-09-03'),
                                                                                                                                        ('Credit Card', '4444444444444444', '2025-09-30', 'Bob Brown', '101', '2024-09-04'),
                                                                                                                                        ('Debit Card', '4555555555555555', '2025-08-31', 'Charlie Williams', '202', '2024-09-05'),
                                                                                                                                        ('Credit Card', '4666666666666666', '2025-07-31', 'Diana Jones', '303', '2024-09-06'),
                                                                                                                                        ('Debit Card', '4777777777777777', '2025-06-30', 'Edward Miller', '404', '2024-09-07'),
                                                                                                                                        ('Credit Card', '4888888888888888', '2025-05-31', 'Fiona Davis', '505', '2024-09-08'),
                                                                                                                                        ('Debit Card', '4999999999999999', '2025-04-30', 'George Martinez', '606', '2024-09-09'),
                                                                                                                                        ('Credit Card', '5000000000000000', '2025-03-31', 'Hannah Garcia', '707', '2024-09-10');


