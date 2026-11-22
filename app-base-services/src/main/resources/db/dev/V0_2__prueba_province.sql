-- =====================================================================
-- SOLO DESARROLLO. Tabla temporal para la entidad heredada ProvinceEntity.
-- BORRAR junto con la entidad y V0_1__prueba_person.sql cuando termine la prueba.
-- (Hibernate pasa "Province" a minúsculas: base_app.province)
-- =====================================================================
CREATE TABLE base_app.province (
    province_id  INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    population   INTEGER
);
