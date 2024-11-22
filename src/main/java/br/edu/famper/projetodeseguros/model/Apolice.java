package br.edu.famper.projetodeseguros.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    @Column(name = "numero", length = 20)
    private String numero;

    @NotNull
    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @NotNull
    @Column(name = "data_fim")
    private LocalDate dataFim;

    @NotNull
    @Column(name = "valor_cobertura")
    private Double valorCobertura;

    @NotNull
    @Column(name = "premio")
    private Double premio;

    @NotNull
    @Column(name = "tipo_seguro", length = 200)
    private String tipoSeguro;

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
