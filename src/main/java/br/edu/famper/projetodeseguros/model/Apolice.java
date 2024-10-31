package br.edu.famper.projetodeseguros.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "apolice")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Hidden
@EqualsAndHashCode(of = "id")
public class Apolice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "numero", length = 20)
    private String numero;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "valor_cobertura")
    private Double valorCobertura;

    @Column(name = "premio")
    private Double premio;

    @OneToMany(mappedBy = "apolice", targetEntity = Sinistro.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Sinistro> sinistros;

    @ManyToOne
    @JoinColumn(name = "segurado_id")
    private Segurado segurado;

    @ManyToOne
    @JoinColumn(name = "corretor_id")
    private Corretor corretor;
}
