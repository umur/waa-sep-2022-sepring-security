INSERT INTO users (id, email, firstname, lastname, password)
VALUES (1, 'admin@admin.com', 'admin', 'admin', 'admin');
INSERT INTO users (id, email, firstname, lastname, password)
VALUES (2, 'user@user.com', 'user', 'user', 'user');

INSERT INTO role (id, role)
VALUES (1, 'admin');
INSERT INTO role (id, role)
VALUES (2, 'user');

INSERT INTO users_roles (user_id, roles_id)
VALUES (1, 1);
INSERT INTO users_roles (user_id, roles_id)
VALUES (2, 2);

-- INSERT INTO product (id, name, price, id_user)
-- VALUES (1, '.NET', 4000, 1);
-- INSERT INTO product (id, name, price, id_user)
-- VALUES (2, 'JAVA', 3000, 1);
-- INSERT INTO product (id, name, price, id_user)
-- VALUES (3, 'Node.js', 1500, 1);