--liquibase formatted sql

--changeset ES:1
--comment: add


-- Создание таблицы users
CREATE TABLE if not exists public.users (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       created_at BIGINT NOT NULL
);
