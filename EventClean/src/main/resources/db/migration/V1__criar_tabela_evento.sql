-- V1_criar_tabela_evento.sql

CREATE TABLE eventos (
                         id BIGSERIAL PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         descricao TEXT,

                         data_inicio TIMESTAMP NOT NULL,
                         data_fim TIMESTAMP NOT NULL,

                         identificador VARCHAR(255) NOT NULL UNIQUE,

                         organizador VARCHAR(255) NOT NULL,

                         capacidade INTEGER NOT NULL,

                         tipo VARCHAR(50) NOT NULL,

                         local_evento VARCHAR(255) NOT NULL
);