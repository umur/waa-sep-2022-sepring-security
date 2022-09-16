/* ROLE */
INSERT INTO role(role) VALUES('admin');
INSERT INTO role(role) VALUES('user');

/* USER */
INSERT INTO users(first_name, last_name, email, password) VALUES('Nodirbek', 'Ergashev', 'nodir@gmail.com', 'password');
INSERT INTO users(first_name, last_name, email, password) VALUES('John', 'Doe', 'john@gmail.com', 'password');

/* USER ROLE */
INSERT INTO users_roles(user_id, roles_id) VALUES(1, 1);
INSERT INTO users_roles(user_id, roles_id) VALUES(2, 2);

/* PRODUCT */
INSERT INTO product(name, price, user_id) VALUES('iPhone 14 Pro', 999, 1);