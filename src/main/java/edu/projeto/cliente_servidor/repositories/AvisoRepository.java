package edu.projeto.cliente_servidor.repositories;

import edu.projeto.cliente_servidor.models.Aviso;
import edu.projeto.cliente_servidor.models.Categoria;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso,Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Aviso a SET a.descricao = :descricao WHERE a.id = :id")
    void updateDescricaoById(@Param("id") Integer id, @Param("descricao") String descricao);

    @Transactional
    @Modifying
    @Query("DELETE FROM Aviso a WHERE a.id = :id")
    void deleteById(@Param("id") Integer id);

    ArrayList<Aviso> findByCategoriaId(Integer idCategoria);

    
}
