-- =====================================================================
-- SOLO DESARROLLO. Tabla temporal para comprobar que el proyecto arranca
-- (Flyway + Hibernate validate). BORRAR este archivo, la entidad Person
-- y la ruta classpath:db/dev del application-dev.yml cuando funcione.
-- Después ejecutar: docker compose down -v
-- =====================================================================
CREATE SCHEMA IF NOT EXISTS base_app;

CREATE TABLE base_app.person (
    person_id            INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name                 VARCHAR(100)  NOT NULL,
    gender               VARCHAR(20)   NOT NULL,
    age                  INTEGER       NOT NULL,
    identification       VARCHAR(20)   NOT NULL UNIQUE,
    status               BOOLEAN       NOT NULL,
    created_date         TIMESTAMP,
    last_modified_date   TIMESTAMP
);
