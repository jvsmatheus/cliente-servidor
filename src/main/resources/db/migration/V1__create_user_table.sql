CREATE TABLE usuarios (
    email VARCHAR(255) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    is_admin BOOLEAN NOT NULL DEFAULT false
);

INSERT INTO usuarios (nome, email, senha, is_admin)
VALUES ('Teste', 'teste@teste.com', '$2a$10$o8GRGghMpvBaWqpFY1IJZ.X6IlMSVgIDI7bZ4YPoR0zZS6swYCMGi', TRUE);
