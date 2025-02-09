CREATE SCHEMA IF NOT EXISTS authentication;

CREATE TABLE IF NOT EXISTS authentication.users
(
    id       bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    username varchar(64) UNIQUE NOT NULL,
    password varchar(128)       NOT NULL,
    role     varchar(32)
)