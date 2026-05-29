DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       surname VARCHAR(255) NOT NULL,
                       person_id VARCHAR(12) NOT NULL UNIQUE,
                       uuid VARCHAR(255) NOT NULL UNIQUE
);

-- test insertu

INSERT INTO users (name, surname, person_id, uuid)
VALUES ('Jan', 'Novak', 'vB1fX4rH7iNt', '11111111-2222-3333-4444-555555555555');
