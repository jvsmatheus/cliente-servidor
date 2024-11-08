package edu.projeto.cliente_servidor.repositories;

import edu.projeto.cliente_servidor.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {
}
