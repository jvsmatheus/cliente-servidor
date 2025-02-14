package edu.projeto.cliente_servidor.dto.aviso;

import edu.projeto.cliente_servidor.models.Categoria;

public record CreateRequestDTO(Categoria categoria, String descricao) {
}
