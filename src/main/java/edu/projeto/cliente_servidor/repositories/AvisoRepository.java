package edu.projeto.cliente_servidor.repositories;

import edu.projeto.cliente_servidor.models.Aviso;
import edu.projeto.cliente_servidor.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso,Integer> {

    @Modifying
    @Query("UPDATE Categoria c SET c.nome = :nome WHERE c.id = :id")
    void updateNomeById(@Param("id") Integer id, @Param("nome") String nome);

    @Modifying
    @Query("DELETE FROM Categoria c WHERE c.id = :id")
    void deleteById(@Param("id") Integer id);

    
}
