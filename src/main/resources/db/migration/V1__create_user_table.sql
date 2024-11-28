CREATE TABLE usuarios (
    email VARCHAR(255) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    is_admin BOOLEAN NOT NULL DEFAULT false
);

INSERT INTO usuarios (nome, email, senha, is_admin)
VALUES ('admin', 'admin@email.com', '$2a$10$EHnnAPQiLBn.ugpEwD7yuOMYFVTytv4WDP6sequX3MnW2QBIZI686', TRUE);
