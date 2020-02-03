INSERT INTO USER (ID, USERNAME, FIRST_NAME, LAST_NAME, EMAIL, ENABLED) VALUES (1, 'admin', 'Admin', 'Administrator', 'admin@email.com', TRUE),(2, 'faboualye', 'Firdaws', 'Aboulaye', 'faboulaye@email.com', TRUE);
INSERT INTO USER (ID, AUTHORITY) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 1), (2, 2);