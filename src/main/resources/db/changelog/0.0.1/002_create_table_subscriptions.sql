--liquibase formatted sql

--changeset ES:2
--comment: add


-- Создание таблицы subscriptions
CREATE TABLE if not exists public.subscriptions (
                               id BIGSERIAL PRIMARY KEY,
                               user_id BIGINT NOT NULL,
                               service_name VARCHAR(255) NOT NULL,
                               start_date BIGINT NOT NULL,
                               price DECIMAL(10, 2) NOT NULL,
                               FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

