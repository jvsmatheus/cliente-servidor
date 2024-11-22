package edu.projeto.cliente_servidor.repositories;

import edu.projeto.cliente_servidor.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {

    Optional<Usuario> findByEmail(String email);

    void delete(Optional<Usuario> user);
}
