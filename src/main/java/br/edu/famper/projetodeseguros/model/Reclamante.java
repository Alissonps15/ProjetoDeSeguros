package br.edu.famper.projetodeseguros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "reclamante")
@Data
public class Reclamante {

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
    @Column(name = "telefone", length = 15)
    private String telefone;

    @NotBlank
    @Column(name = "email", length = 200)
    private String email;



    @ManyToOne
    @JoinColumn(name = "sinistro_id")
    private Sinistro sinistro;
}