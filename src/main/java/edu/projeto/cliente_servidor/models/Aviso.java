package edu.projeto.cliente_servidor.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "avisos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    @Column(name = "id_categoria")
    private Integer idCategoria;

    public Aviso(String descricao, Integer idCategoria) {
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }
}
