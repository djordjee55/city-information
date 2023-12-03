CREATE TABLE t_city
(
    id                      SERIAL          PRIMARY KEY,
    name                    VARCHAR         NOT NULL,
    country                 VARCHAR         NOT NULL,
    state_or_region         VARCHAR,
    population              BIGINT          NOT NULL,
    UNIQUE(name, country)
)