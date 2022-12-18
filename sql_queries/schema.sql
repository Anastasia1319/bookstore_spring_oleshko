CREATE TABLE books (
	"id" BIGSERIAL PRIMARY KEY,
	author VARCHAR(50),
	title VARCHAR(100),
	publishin_year CHAR(4),
	isbn CHAR(13) UNIQUE NOT NULL,
	price DECIMAL(8, 2)
);

CREATE TABLE "role" (
	role_id BIGSERIAL PRIMARY KEY,
	name_role VARCHAR(50)
);

CREATE TABLE users (
	user_id BIGSERIAL PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50),
	email VARCHAR(50) NOT NULL,
	"password" CHAR(8) NOT NULL,
	role_id INT NOT NULL,
	FOREIGN KEY (role_id) REFERENCES "role" (role_id)
);