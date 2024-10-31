package br.edu.famper.projetodeseguros.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "corretor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Corretor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "cnpj", length = 14)
    private String cnpj;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "telefone", length = 11)
    private String telefone;

    @OneToMany(mappedBy = "corretor", targetEntity = Apolice.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Apolice> apolices;
}