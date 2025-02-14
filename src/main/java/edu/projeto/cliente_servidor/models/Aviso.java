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
//    @Column(name = "id_categoria")
    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    public Aviso(String descricao, Categoria categoria) {
        this.descricao = descricao;
        this.categoria = categoria;
    }
}
