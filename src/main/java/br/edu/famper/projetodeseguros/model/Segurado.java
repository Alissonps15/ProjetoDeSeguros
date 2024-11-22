package br.edu.famper.projetodeseguros.model;

import jakarta.persistence.*;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "segurado")
@Data
public class Segurado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "nome", length = 100)
    private String nome;

    @NotBlank
    @Column(name = "cpf_cnpj", length = 14)
    private String cpfCnpj;

    @NotBlank
    @Column(name = "endereco", length = 200)
    private String endereco;

    @NotBlank
    @Column(name = "telefone", length = 15)
    private String telefone;

    @NotBlank
    @Column(name = "email", length = 100)
    private String email;

    @OneToMany(mappedBy = "segurado", targetEntity = Apolice.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Apolice> apolices;
}