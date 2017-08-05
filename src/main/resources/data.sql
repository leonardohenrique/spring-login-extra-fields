INSERT INTO roles (id, name) VALUES 
(1, 'ADMIN'),
(2, 'USER');

INSERT INTO users (id, name, email, username, password, company) VALUES 
(1, 'Admin', 'admin@email.com', 'admin', '$2a$06$NltIblGMRhay71xLBmdJKeMmFounIORe/id51DwkUIiAT8w74dqU6', 'AAA'),
(2, 'Leonardo', 'leonardo@email.com', 'leonardo', '$2a$06$NltIblGMRhay71xLBmdJKeMmFounIORe/id51DwkUIiAT8w74dqU6', 'BBB');

INSERT INTO users_roles (user_id, roles_id) VALUES 
(1, 1), 
(2, 2);