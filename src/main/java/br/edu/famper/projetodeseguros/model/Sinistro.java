package br.edu.famper.projetodeseguros.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "sinistro")
@Data
public class Sinistro {
    //https://www.youtube.com/watch?v=Eg-eOQfmrUE
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "data_ocorrencia")
    private LocalDate dataOcorrencia;

    @NotBlank
    @Column(name = "descricao", length = 200)
    private String descricao;

    @NotNull
    @Column(name = "valor_reclamado")
    private Double valorReclamado;



    @ManyToOne
    @JoinColumn(name = "apolice_id")
    private Apolice apolice;

    @OneToMany(mappedBy = "sinistro", targetEntity = Reclamante.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reclamante> reclamantes;

}