package br.edu.famper.projetodeseguros.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reclamante")
@Data
public class Reclamante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "cpf_cnpj", length = 14)
    private String cpfCnpj;

    @Column(name = "telefone", length = 15)
    private String telefone;

    @Column(name = "email", length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "sinistro_id")
    private Sinistro sinistro;
}