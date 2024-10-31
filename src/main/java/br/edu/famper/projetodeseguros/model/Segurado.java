package br.edu.famper.projetodeseguros.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "segurado")
@Data
public class Segurado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "cpf_cnpj", length = 14)
    private String cpfCnpj;

    @Column(name = "endereco", length = 255)
    private String endereco;

    @Column(name = "telefone", length = 15)
    private String telefone;

    @Column(name = "email", length = 100)
    private String email;

    @OneToMany(mappedBy = "segurado", targetEntity = Apolice.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Apolice> apolices;
}