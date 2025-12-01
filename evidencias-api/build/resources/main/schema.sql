CREATE TABLE IF NOT EXISTS arquivos (
    id      SERIAL          PRIMARY KEY,
    caminho VARCHAR(255)    NOT NULL UNIQUE,
    nome    VARCHAR(255)    NOT NULL UNIQUE
);