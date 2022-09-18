
INSERT INTO category(id,name) values (1,'smart-phone');
INSERT INTO category(id,name) values (2,'smart-watches');

INSERT INTO product(id, name, user_id, category_id) values (1,'Iphone-14',1,1);
INSERT INTO product(id,name,user_id,category_id) values (2,'I-phone13',1,1);


INSERT INTO role(id,name) values (1,'ADMIN');
INSERT INTO role(id,name) values (2,'USER');



INSERT INTO users(id, email, password) VALUES (1,'hiwot.alemayehu@miu.edu','1234');
INSERT INTO users(id, email, password) VALUES (2,'kidist.alemayehu@miu.edu','');

INSERT INTO users_roles(users_id, roles_id) VALUES (1,1);
INSERT INTO users_roles(users_id, roles_id) VALUES (2,2);
