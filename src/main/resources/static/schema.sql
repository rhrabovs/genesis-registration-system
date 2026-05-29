DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       surname VARCHAR(255) NOT NULL,
                       person_id VARCHAR(12) NOT NULL UNIQUE,
                       uuid VARCHAR(255) NOT NULL UNIQUE
);

