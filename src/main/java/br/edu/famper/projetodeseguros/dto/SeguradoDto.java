package br.edu.famper.projetodeseguros.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(title = "Segurado", description = "Dados do Segurado")
public class SeguradoDto {

    @Schema(description = "ID do Segurado", example = "1", title = "ID")
    private Long id;

    @Schema(description = "Nome do Segurado", example = "Pedro Pinto no Rego", title = "Nome")
    private String nome;

    @Schema(description = "CPF/CNPJ do Segurado", example = "123.456.789-09", title = "CPF/CNPJ")
    private String cpfCnpj;

    @Schema(description = "Endereço do Segurado", example = "Rua Exemplo, 123", title = "Endereço")
    private String endereco;

    @Schema(description = "Telefone do Segurado", example = "(46)99934-5678", title = "Telefone")
    private String telefone;

    @Schema(description = "E-mail do Segurado", example = "PedroPintodaSilva@gmail.com", title = "E-mail")
    private String email;

    @Schema(description = "Lista de Apólices do Segurado", title = "Apólices")
    private List<ApoliceDto> apolices;
}