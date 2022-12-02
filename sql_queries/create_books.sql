CREATE TABLE books (
	"id" BIGSERIAL PRIMARY KEY,
	author VARCHAR(50),
	title VARCHAR(100),
	publishin_year CHAR(4),
	isbn CHAR(13) UNIQUE NOT NULL,
	price DECIMAL(8, 2)
);