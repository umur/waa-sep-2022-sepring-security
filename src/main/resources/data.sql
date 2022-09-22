INSERT INTO user_table (id, email, username, password, is_enabled)
VALUES (1, 'test_user@example.com', 'test_user', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', true),
       (2, 'test_user_2@example.com', 'test_user_2', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2',
        true),
       (3, 'test_user_3@example.com', 'test_user_3', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2',
        false),
       (4, 'admin@example.com', 'admin', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2',
        false),
       (5, 'user@example.com', 'user', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2',
        false);

INSERT INTO role (id, name, user_id)
VALUES (1, 'ADMIN', 1),
       (2, 'USER', 1),
       (3, 'USER', 2),
       (4, 'SYS_ADMIN', 3),
       (5, 'ADMIN', 4),
       (6, 'USER', 5);

INSERT INTO category (id, name)
VALUES (1, 'phones'),
       (2, 'tables'),
       (3, 'grocery'),
       (4, 'laptops');

INSERT INTO product (id, name, price, user_id, category_id)
VALUES (1, 'iphone', 455, 1, 1),
       (2, 'ipad', 500, 1, 2),
       (3, 'samsung', 650, 2, 1),
       (4, 'tab', 600, 2, 2),
       (5, 'lenovo', 300, 3, 4),
       (6, 'dell', 700, 3, 4);