INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');

INSERT INTO users (username, password, name, surname, email, role_id) VALUES ('vokutra', '$2a$10$I2uhMNxhwlYBz9gfrRbY0OKCZ0vPX5ev6cHzb2QnGdZTP9JkuOoJy', 'Katarina', 'Artukov', 'kartukov12@gmail.com', 2);
INSERT INTO users (username, password, name, surname, email, role_id) VALUES ('admin', '$2a$10$I2uhMNxhwlYBz9gfrRbY0OKCZ0vPX5ev6cHzb2QnGdZTP9JkuOoJy', 'admin', 'admin', 'admin@lawly.com', 1);

INSERT INTO offers (id, name, price) VALUES ('9cca5e41-39a0-4be9-9d11-c605a32703a4', 'Codification of laws', 5200);
INSERT INTO offers (id, name, price) VALUES ('9cca5e41-39a0-4be9-9d11-c605a32703a5', 'Issuance of laws in paper form', 4500);
INSERT INTO offers (id, name, price) VALUES ('9cca5e41-39a0-4be9-9d11-c605a32703a6', 'Issuance of laws in electronic form', 3800);

