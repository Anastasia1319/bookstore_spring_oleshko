/*
DELETE IF EXISTS TABLE order_items;
DELETE IF EXISTS TABLE orders;
DELETE IF EXISTS TABLE users;
DELETE IF EXISTS TABLE books;
*/

CREATE TABLE IF NOT EXISTS books (
	"id" BIGSERIAL PRIMARY KEY,
	author VARCHAR(300),
	title VARCHAR(300),
	publishing_year INT,
	isbn CHAR(13) UNIQUE NOT NULL,
	price DECIMAL(8, 2),
    deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS users (
    "id" BIGSERIAL PRIMARY KEY,
	first_name VARCHAR(100),
	last_name VARCHAR(100),
	email VARCHAR(100) NOT NULL UNIQUE,
	"password" CHAR(40) NOT NULL,
	role VARCHAR(50) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE
);


CREATE TABLE IF NOT EXISTS orders (
    "id" BIGSERIAL PRIMARY KEY,
    user_id INT8 NOT NULL,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users ("id")
    );

CREATE TABLE IF NOT EXISTS order_items (
    "id" BIGSERIAL PRIMARY KEY,
    book_id INT8 NOT NULL,
    quantity INT4 NOT NULL,
    price NUMERIC(9,2) NOT NULL,
    order_id INT8 NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books ("id"),
    FOREIGN KEY (order_id) REFERENCES orders ("id")
);
