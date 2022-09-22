INSERT INTO Address(street, zip, city) VALUES ('100 1st Street', '52556', 'Iowa'),
                           ('200 1st St', '52556', 'Iowa');

INSERT INTO Category(name) VALUES ('food'),
                                    ('electronics');

INSERT INTO Role(role) VALUES ('user'), ('admin');

INSERT INTO Product(name, price, rating, category_id) VALUES ('candy', 10, 5.0, 1),
                                                             ('bread', 15, 4.5, 1),
                                                             ('ipad', 700, 4.8, 2),
                                                             ('iphone', 900, 4.9, 2);

INSERT INTO Lab_User(email, first_name, last_name, password, address_id) VALUES ('steve@gmail.com', 'Steve', 'Good', '$2y$10$dnQfAD5oBnFs7O7R1BHJu.hZ68dv5vWhvw42GiP7BVHxTELYkwfhe', 1),
                                                                                ('henry@gmail.com', 'Henry', 'Roll', '$2y$10$dnQfAD5oBnFs7O7R1BHJu.hZ68dv5vWhvw42GiP7BVHxTELYkwfhe', 2),
                                                                                ('user1@gmail.com', 'First', 'User', '$2y$10$dnQfAD5oBnFs7O7R1BHJu.hZ68dv5vWhvw42GiP7BVHxTELYkwfhe', 1),
                                                                                ('admin1@gmail.com', 'First', 'Admin', '$2y$10$dnQfAD5oBnFs7O7R1BHJu.hZ68dv5vWhvw42GiP7BVHxTELYkwfhe', 2);

INSERT INTO Lab_User_Roles(user_id, roles_id) VALUES (1, 1), (2, 2), (3, 1), (4, 2);

INSERT INTO Review(comment, user_id) VALUES ('This is very good', 1),
                                            ('Awesome product', 1),
                                            ('It is ok', 2);