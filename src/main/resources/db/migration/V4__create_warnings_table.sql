CREATE TABLE avisos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(500) NOT NULL,
    id_categoria INT,
    CONSTRAINT FK_AvisosCategorias FOREIGN KEY (id_categoria)
    REFERENCES Categorias(id)
);