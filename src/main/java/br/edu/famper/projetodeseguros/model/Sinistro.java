package br.edu.famper.projetodeseguros.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "sinistro")
@Data
public class Sinistro {
    //https://www.youtube.com/watch?v=Eg-eOQfmrUE
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data_ocorrencia")
    private LocalDate dataOcorrencia;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "valor_reclamado")
    private Double valorReclamado;

    @ManyToOne
    @JoinColumn(name = "apolice_id")
    private Apolice apolice;

    @OneToMany(mappedBy = "sinistro", targetEntity = Reclamante.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reclamante> reclamantes;

}