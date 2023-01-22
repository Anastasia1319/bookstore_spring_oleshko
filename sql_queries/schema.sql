/*
DELETE IF EXISTS TABLE order_items;
DELETE IF EXISTS TABLE orders;
DELETE IF EXISTS TABLE statuses;
DELETE IF EXISTS TABLE users;
DELETE IF EXISTS TABLE roles;
DELETE IF EXISTS TABLE books;
*/

CREATE TABLE IF NOT EXISTS books (
    "id" BIGSERIAL PRIMARY KEY,
    author VARCHAR(50),
    title VARCHAR(100),
    publishin_year CHAR(4),
    isbn CHAR(13) UNIQUE NOT NULL,
    price DECIMAL(8, 2),
    deleted BOOLEAN DEFAULT FALSE
    );

CREATE TABLE IF NOT EXIST users (
    user_id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    email VARCHAR(50) NOT NULL UNIQUE,
    "password" CHAR(8) NOT NULL,
    role VARCHAR(50) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE
    );


CREATE TABLE IF NOT EXIST orders (
     "id" BIGSERIAL PRIMARY KEY,
     user_id INT8 NOT NULL,
     status VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
    );

CREATE TABLE IF NOT EXIST order_items (
    "id" BIGSERIAL PRIMARY KEY,
    book_id INT8 NOT NULL,
    quantity INT4 NOT NULL,
    price NUMERIC(9,2) NOT NULL,
    order_id INT8 NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books ("id"),
    FOREIGN KEY (order_id) REFERENCES orders ("id")
    );