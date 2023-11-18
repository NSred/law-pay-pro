INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');

INSERT INTO users (username, password, name, surname, email, role_id) VALUES ('vokutra', '$2a$10$I2uhMNxhwlYBz9gfrRbY0OKCZ0vPX5ev6cHzb2QnGdZTP9JkuOoJy', 'Katarina', 'Artukov', 'kartukov12@gmail.com', 1);

INSERT INTO offers (name, price) VALUES ('Codification of laws', 5200);
INSERT INTO offers (name, price) VALUES ('Issuance of laws in paper form', 4500);
INSERT INTO offers (name, price) VALUES ('Issuance of laws in electronic form', 3800);

