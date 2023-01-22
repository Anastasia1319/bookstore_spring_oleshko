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
       ('Franz Kafka', 'Process', 2015, '5-17-089289-1', 10.33),
       ('Jerome David Salinger', 'The Catcher in the Rye', 2022, '5-04-113242-2', 25.32),
       ('Harper Lee', 'To Kill a Mockingbird', 2021, '5-17-090411-2', 16.03),
       ('Jack London', 'The Little Lady of the Big House', 2016, '5-17-100401-9', 10.33),
       ('Lewis Carroll', 'Alice in Wonderland', 2015, '5-17-103240-1', 16.33),
       ('Emily Jane Bronte', 'Wuthering Heights', 2022, '5-389-01323-0', 9.19),
       ('Colleen McCullough', 'Singing in the thorns', 2022, '5-17-147429-4', 29.17);


INSERT INTO users (first_name, last_name, email, "password", role)
VALUES ('Oliver', 'Phelps', 'oliver4444@jmail.com', '15975328', 'ADMIN'),
       ('Jack', 'Black', 'jack13@jmail.com', '11111111', 'CUSTOMER'),
       ('Teresa', 'Gilmore', 'teresa@jmail.com', '24863157', 'MANAGER'),
       ('Harry', 'Floyd', 'harry@jmail.com', '12131516', 'CUSTOMER'),
       ('Willis', 'Scott', 'will@jmail.com', '75315982', 'MANAGER'),
       ('Pauline', 'Patrick', 'pauline@jmail.com', '99999999', 'CUSTOMER'),
       ('Fay', 'Warren', 'fay777@jmail.com', '55557777', 'CUSTOMER'),
       ('Laurel', 'Murphy', 'laurel@jmail.com', '13151719', 'ADMIN'),
       ('Candice', 'Mosley', 'candy@jmail.com', '45454545', 'CUSTOMER'),
       ('Victoria', 'Holmes', 'viki@jmail.com', '7595ai23', 'MANAGER'),
       ('Ronald', 'Fox', 'fox@jmail.com', '75315384', 'CUSTOMER'),
       ('Piers', 'Wilkerson', 'piers@jmail.com', '24936578', 'MANAGER'),
       ('Hector', 'Goodwin', 'goodwin@jmail.com', '55555555', 'CUSTOMER'),
       ('Dominick', 'Robbins', 'dom@jmail.com', '15171819', 'CUSTOMER'),
       ('Patricia', 'Henry', 'henry@jmail.com', '10000001', 'CUSTOMER'),
       ('Margaret', 'Marshall', 'maggy@jmail.com', '5657aaoi', 'CUSTOMER'),
       ('Meryl', 'Osborne', 'meryl@jmail.com', 'mosborne', 'CUSTOMER'),
       ('Colin', 'Wood', 'wood@jmail.com', 'wood1313', 'CUSTOMER'),
       ('Rudolph', 'Gilbert ', 'rudy@jmail.com', '1213aaaa', 'CUSTOMER'),
       ('Isabella', 'Garrett', 'isabella@jmail.com', '13141583', 'ADMIN'),
       ('Susanna ', 'Watts', 'susy@jmail.com', '45494863', 'MANAGER'),
       ('Basil', 'Rodgers', 'basil@jmail.com', '35762524', 'CUSTOMER'),
       ('Michael', 'Mosley', 'micky@jmail.com', '25289113', 'CUSTOMER'),
       ('Virginia', 'McGee', 'vivi@jmail.com', '17181920', 'CUSTOMER'),
       ('Cameron', 'Wilkinson', 'wilkinson@jmail.com', 'awdszeyt', 'MANAGER'),
       ('Lilian', 'Payne', 'lili@jmail.com', 'lol77lol', 'CUSTOMER');


INSERT INTO orders (user_id, status)
VALUES (3, 'PENDING'),
       (13, 'DELIVERED'),
       (24, 'PAID'),
       (8, 'CANCELED'),
       (1, 'PENDING'),
       (6, 'DELIVERED'),
       (10, 'PENDING'),
       (11, 'PAID'),
       (5, 'PENDING'),
       (7, 'DELIVERED'),
       (21, 'DELIVERED'),
       (23, 'PAID'),
       (18, 'CANCELED'),
       (13, 'CANCELED'),
       (3, 'DELIVERED'),
       (26, 'PENDING'),
       (1, 'CANCELED'),
       (19, 'PAID'),
       (15, 'PAID'),
       (22, 'CANCELED'),
       (17, 'DELIVERED'),
       (7, 'CANCELED'),
       (6, 'PENDING'),
       (17, 'DELIVERED'),
       (4, 'DELIVERED'),
       (2, 'CANCELED'),
       (14, 'PENDING'),
       (11, 'PAID'),
       (25, 'PAID');

INSERT INTO order_items (book_id, quantity, price, order_id)
VALUES (1, 2, (SELECT b.price FROM books b WHERE b.id = 1), 1),
       (3, 1, (SELECT b.price FROM books b WHERE b.id = 3), 1),
       (19, 5, (SELECT b.price FROM books b WHERE b.id = 19), 1),
       (2, 1, (SELECT b.price FROM books b WHERE b.id = 2), 2),
       (17, 3, (SELECT b.price FROM books b WHERE b.id = 17), 3),
       (17, 1, (SELECT b.price FROM books b WHERE b.id = 17), 4),
       (11, 10, (SELECT b.price FROM books b WHERE b.id = 11), 5),
       (29, 1, (SELECT b.price FROM books b WHERE b.id = 29), 5),
       (13, 3, (SELECT b.price FROM books b WHERE b.id = 13), 6),
       (3, 1, (SELECT b.price FROM books b WHERE b.id = 3), 7),
       (7, 4, (SELECT b.price FROM books b WHERE b.id = 7), 8),
       (5, 1, (SELECT b.price FROM books b WHERE b.id = 5), 9),
       (18, 1, (SELECT b.price FROM books b WHERE b.id = 18), 9),
       (10, 10, (SELECT b.price FROM books b WHERE b.id = 10), 10),
       (26, 6, (SELECT b.price FROM books b WHERE b.id = 26), 11),
       (20, 2, (SELECT b.price FROM books b WHERE b.id = 20), 12),
       (13, 13, (SELECT b.price FROM books b WHERE b.id = 13), 13),
       (1, 8, (SELECT b.price FROM books b WHERE b.id = 1), 14),
       (4, 5, (SELECT b.price FROM books b WHERE b.id = 4), 15),
       (1, 2, (SELECT b.price FROM books b WHERE b.id = 1), 15),
       (8, 1, (SELECT b.price FROM books b WHERE b.id = 8), 16),
       (19, 4, (SELECT b.price FROM books b WHERE b.id = 19), 17),
       (14, 1, (SELECT b.price FROM books b WHERE b.id = 14), 18),
       (16, 2, (SELECT b.price FROM books b WHERE b.id = 16), 19),
       (22, 3, (SELECT b.price FROM books b WHERE b.id = 22), 20),
       (18, 2, (SELECT b.price FROM books b WHERE b.id = 18), 21),
       (26, 7, (SELECT b.price FROM books b WHERE b.id = 26), 22),
       (9, 8, (SELECT b.price FROM books b WHERE b.id = 9), 22),
       (18, 1, (SELECT b.price FROM books b WHERE b.id = 18), 23),
       (8, 1, (SELECT b.price FROM books b WHERE b.id = 8), 24),
       (9, 1, (SELECT b.price FROM books b WHERE b.id = 9), 25),
       (8, 1, (SELECT b.price FROM books b WHERE b.id = 8), 25),
       (10, 1, (SELECT b.price FROM books b WHERE b.id = 10), 26),
       (8, 1, (SELECT b.price FROM books b WHERE b.id = 8), 26),
       (28, 2, (SELECT b.price FROM books b WHERE b.id = 28), 27),
       (27, 1, (SELECT b.price FROM books b WHERE b.id = 27), 27),
       (12, 1, (SELECT b.price FROM books b WHERE b.id = 12), 28),
       (9, 1, (SELECT b.price FROM books b WHERE b.id = 9), 29),
       (18, 1, (SELECT b.price FROM books b WHERE b.id = 18), 29);