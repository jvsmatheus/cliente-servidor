package edu.projeto.cliente_servidor.repositories;

import edu.projeto.cliente_servidor.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {

    Optional<Usuario> findByEmail(String email);

    void deleteByEmail(String email);
}
