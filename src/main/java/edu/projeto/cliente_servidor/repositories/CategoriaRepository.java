package edu.projeto.cliente_servidor.repositories;

import edu.projeto.cliente_servidor.models.Categoria;
import edu.projeto.cliente_servidor.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

    @Modifying
    @Query("UPDATE Categoria c SET c.nome = :nome WHERE c.id = :id")
    void updateNomeById(@Param("id") Integer id, @Param("nome") String nome);

    @Modifying
    @Query("DELETE FROM Categoria c WHERE c.id = :id")
    void deleteById(@Param("id") Integer id);

    
}
