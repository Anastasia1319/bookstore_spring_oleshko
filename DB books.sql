CREATE TABLE books (
	"id" BIGSERIAL PRIMARY KEY,
	author VARCHAR(50),
	title VARCHAR(100),
	publishin_year CHAR(4),
	isbn CHAR(13) UNIQUE NOT NULL,
	price DECIMAL(8, 2)
);

INSERT INTO books (author, title, publishin_year, isbn, price)
VALUES ('Oscar Wilde', 'The Picture of Dorian Grey', 2019, '5-17-099056-6', 9.86),
		('Francis Burnett', 'Secret garden', 2020, '5-17-119978-4', 9.86),
		('Robert Stevenson', 'Suicide Club', 2020, '5-17-116914-5', 9.04),
		('Erich Maria Remarque', 'Triumphal Arch', 2017, '5-17-105398-7', 15.76),
		('Michael Bulgakov', 'Morphine', 2016, '5-17-095618-0', 8.61),
		('William Golding', 'Lord of the Flies', 2019, '5-17-080086-5', 12.97),
		('Walter Tevis', 'Queen move', 2020, '5-17-134612-6', 25.26),
		('Theodore Dreiser', 'Financier. Titanium. Stoic', 2022, '5-389-14227-5', 55.39),
		('Francis Scott Fitzgerald', 'The Great Gatsby', 2022, '5-7516-1763-9', 32.17),
		('Francis Scott Fitzgerald', 'Night is tender', 2022, '5-04-173525-8', 20.22),
		('Antoine de Saint-Exupery', 'The little Prince', 2018, '5-699-90130-2', 12.83),
		('Erich Maria Remarque', 'Night in Lisbon', 2019, '5-17-113150-0', 16.91),
		('Bram Stoker', 'Dracula', 2022, '5-04-156957-0', 16.83),
		('Edgar Poe', 'Murder in the Rue Morgue', 2022, '5-08-006896-6', 45.37),
		('Edgar Poe', 'Crow. Full composition of writings', 2022, '5-389-14377-7', 66.44),
		('Dante Alighieri', 'The Divine Comedy', 2019, '5-389-05867-5', 10.69),
		('Dante Alighieri', 'New life', 2018, '5-386-10627-0', 27.90),
		('Daniel Keyes', 'Flowers for Algernon', 2021, '5-699-55699-1', 19.80),
		('Daniel Keyes', 'The Mysterious Case of Billy Milligan', 2021, '5-04-112564-6', 18.90),
		('Brothers Grimm', 'True Tales of the Brothers Grimm', 2018, '5-906979-68-1', 75.35),
		('Michael Bulgakov', 'Heart of dog', 2013, '5-389-06294-8', 10.69),
		('Aldous Huxley', 'Oh brave new world', 2013, '5-17-062823-0', 18.69),
		('Franz Kafka', 'Process', 2015, '5-17-089289-1', 10.33);
		
SELECT * FROM books;

SELECT COUNT(*) FROM books;