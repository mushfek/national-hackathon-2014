INSERT INTO user (version,email,enabled,locked,password,username) VALUES (0, 'admin@test.com', 1, 0, '123456', 'admin');
INSERT INTO user_role (role,user_id) VALUES ( '0', '1');
INSERT INTO user_role (role,user_id) VALUES ( '1', '1');
