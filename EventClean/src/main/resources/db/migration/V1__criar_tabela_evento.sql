-- V1_criar_tabela_evento.sql
CREATE TABLE Eventos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    identificador VARCHAR(255) NOT NULL  UNIQUE,
    dataInicio TIMESTAMP NOT NULL,
    dataFim TIMESTAMP NOT NULL,
    local_evento VARCHAR(255) NOT NULL ,
    organizador VARCHAR(255) NOT NULL ,
    capacidade INTEGER NOT NULL,
    tipo VARCHAR(50) NOT NULL
);