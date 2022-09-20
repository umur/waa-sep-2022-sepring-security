# INSERT INTO product (id, name, price, rating) values (1, 'iphone', 1200, 4);
# INSERT INTO product (id, name, price, rating) values ('mac', 2400, 5);
# INSERT INTO product (id, name, price, rating) values ( 'hp', 100, 3);
# INSERT INTO role (id, role) values ( 1, 'admin' );
# INSERT INTO role (id, role) values ( 2, 'user' );

# INSERT INTO user (id, role) values ( 2, 'user' );

INSERT INTO user (id, email, first_name, last_name, password)
VALUES (1, 'uinan@miu.edu', 'umur', 'inan', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123
INSERT INTO user (id, email, first_name, last_name, password)
VALUES (2, 'john@miu.edu', 'john', 'doe', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123

INSERT INTO user (id, email, first_name, last_name, password)
VALUES (3, 'redi@miu.edu', 'redi', 'mulu', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123

INSERT INTO role (id, role)
VALUES (1, 'ADMIN');
INSERT INTO role (id, role)
VALUES (2, 'USER');


INSERT INTO user_roles (user_id, roles_id)
VALUES (1, 1);
INSERT INTO user_roles (user_id, roles_id)
VALUES (2, 2);
INSERT INTO user_roles (user_id, roles_id)
VALUES (3, 1);

INSERT INTO category (id, name)
VALUES (1, 'food');
INSERT INTO category (id, name)
VALUES (2, 'cloth');

INSERT INTO product (id, name, price,rating,category_id)
VALUES (1, 'iPhone', 1200, 3, 1);
INSERT INTO product (id, name, price,rating,category_id)
VALUES (2, 'iPad', 900, 4, 1);
INSERT INTO product (id, name, price,rating,category_id)
VALUES (3, 'Pen', 5, 1, 1);