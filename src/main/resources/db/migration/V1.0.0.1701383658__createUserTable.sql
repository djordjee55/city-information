CREATE TABLE t_user
(
    id                  SERIAL          PRIMARY KEY,
    username            VARCHAR         UNIQUE NOT NULL,
    encoded_password    VARCHAR         NOT NULL
)