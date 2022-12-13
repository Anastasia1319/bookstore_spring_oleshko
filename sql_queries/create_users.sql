CREATE TABLE users (
	user_id BIGSERIAL PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50),
	email VARCHAR(50) NOT NULL,
	"password" CHAR(8) NOT NULL,
	role_id INT NOT NULL,
	FOREIGN KEY (role_id) REFERENCES "role" (role_id)
);