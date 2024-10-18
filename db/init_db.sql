CREATE TABLE IF NOT EXISTS customers (
    pk_customer_id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    address TEXT,
    categories_preferences TEXT[]
);

CREATE TABLE IF NOT EXISTS prices (
    pk_price_id BIGSERIAL PRIMARY KEY,
    currency_code CHAR(3) NOT NULL,
    amount NUMERIC(10, 2) NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS categories (
    pk_category_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    type VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS inventories (
    pk_inventory_id BIGSERIAL PRIMARY KEY,
    sku VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
    pk_product_id BIGSERIAL PRIMARY KEY,
    fk_inventory_id BIGINT UNIQUE,
    fk_price_id BIGINT,           
    name VARCHAR(255) NOT NULL,
    description TEXT,
    FOREIGN KEY (fk_inventory_id) REFERENCES inventories(pk_inventory_id) ON DELETE SET NULL,
    FOREIGN KEY (fk_price_id) REFERENCES prices(pk_price_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS products_categories (
    pk_product_category_id BIGSERIAL PRIMARY KEY,
    fk_product_id BIGINT,                       
    fk_category_id BIGINT,                      
    FOREIGN KEY (fk_product_id) REFERENCES products(pk_product_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_category_id) REFERENCES categories(pk_category_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS address_information (
    pk_address_information_id BIGSERIAL PRIMARY KEY,
    street VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    zip_code VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS locations (
    pk_location_id BIGSERIAL PRIMARY KEY,
    fk_address_id BIGINT,
    width_cm INTEGER,
    height_cm INTEGER,
    depth_cm INTEGER,
    FOREIGN KEY (fk_address_id) REFERENCES address_information(pk_address_information_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS stock_entry (
    pk_stock_entry_id BIGSERIAL PRIMARY KEY,
    fk_location_id BIGINT,
    fk_inventory_id BIGINT,
    measurement_unit VARCHAR(50) NOT NULL,
    quantity BIGINT NOT NULL,
    FOREIGN KEY (fk_location_id) REFERENCES locations(pk_location_id) ON DELETE SET NULL,
    FOREIGN KEY (fk_inventory_id) REFERENCES inventories(pk_inventory_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS shipping_information (
    pk_shipping_information_id BIGSERIAL PRIMARY KEY,
    fk_address_information_id BIGINT,
    fk_price_id BIGINT,
    tracking_number VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    status VARCHAR(25) NOT NULL,
    FOREIGN KEY (fk_address_information_id) REFERENCES address_information(pk_address_information_id) ON DELETE SET NULL,
    FOREIGN KEY (fk_price_id) REFERENCES prices(pk_price_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS billing_information (
    pk_billing_information_id BIGSERIAL PRIMARY KEY,
    fk_address_information_id BIGINT,
    fk_price_id BIGINT,              
    date DATE NOT NULL,
    FOREIGN KEY (fk_address_information_id) REFERENCES address_information(pk_address_information_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_price_id) REFERENCES prices(pk_price_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS payment_details (
    pk_payment_details_id BIGSERIAL PRIMARY KEY,
    payment_method_type VARCHAR(25) NOT NULL,
    card_number VARCHAR(25) NOT NULL,
    card_expiration_date DATE NOT NULL,
    card_holder_name VARCHAR(100) NOT NULL,
    card_cvv VARCHAR(3) NOT NULL,
    date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (
    pk_order_id BIGSERIAL PRIMARY KEY,
    fk_shipping_information_id BIGINT,
    fk_billing_information_id BIGINT,
    fk_payment_details_id BIGINT,
    fk_customer_id BIGINT,
    date DATE NOT NULL,
    status VARCHAR(25) NOT NULL,
    total_usd NUMERIC(10, 2) NOT NULL, -- THIS IS THE SUM OF ALL PRODUCTS IN THE ORDER. Should be calculated
    FOREIGN KEY (fk_shipping_information_id) REFERENCES shipping_information(pk_shipping_information_id) ON DELETE SET NULL,
    FOREIGN KEY (fk_billing_information_id) REFERENCES billing_information(pk_billing_information_id) ON DELETE SET NULL,
    FOREIGN KEY (fk_payment_details_id) REFERENCES payment_details(pk_payment_details_id) ON DELETE SET NULL,
    FOREIGN KEY (fk_customer_id) REFERENCES customers(pk_customer_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS products_orders (
    pk_product_order_id BIGSERIAL PRIMARY KEY,
    fk_product_id BIGINT,             
    fk_order_id BIGINT,              
    FOREIGN KEY (fk_product_id) REFERENCES products(pk_product_id) ON DELETE CASCADE,
    FOREIGN KEY (fk_order_id) REFERENCES orders(pk_order_id) ON DELETE CASCADE
);