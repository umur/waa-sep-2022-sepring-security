INSERT INTO users (id, email, firstname, lastname, password)
VALUES (default, 'amgalan@miu.edu', 'Amgalan', 'Bat-Erdene', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123
INSERT INTO users (id, email, firstname, lastname, password)
VALUES (default, 'saintur@miu.edu', 'Saintur', 'Batkhuu', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123
INSERT INTO users (id, email, firstname, lastname, password)
VALUES (default, 'zolzaya@miu.edu', 'Zolzaya', 'Bayantsogt', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123

INSERT INTO role (id, role)
VALUES (default, 'ADMIN');
INSERT INTO role (id, role)
VALUES (default, 'USER');

INSERT INTO users_roles (user_id, roles_id)
VALUES (1, 1);
INSERT INTO users_roles (user_id, roles_id)
VALUES (2, 2);
INSERT INTO users_roles (user_id, roles_id)
VALUES (3, 2);

INSERT INTO product (id, name, price, id_user)
VALUES (default, 'iPhone', 1200, 1);
INSERT INTO product (id, name, price, id_user)
VALUES (default, 'iPad', 900, 2);
INSERT INTO product (id, name, price, id_user)
VALUES (default, 'Pen', 5, 3);