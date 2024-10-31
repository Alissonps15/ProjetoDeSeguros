package br.edu.famper.projetodeseguros.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(title = "Corretor", description = "Dados do Corretor")
public class CorretorDto {

    @Schema(description = "ID do Corretor", example = "1", title = "id")
    private Long id;

    @Schema(description = "Nome do Corretor", example = "Ariel da Silva", title = "nome")
    private String nome;

    @Schema(description = "CNPJ do Corretor", example = "123.456.789/0001-01", title = "CNPJ")
    private String cnpj;

    @Schema(description = "E-mail do Corretor", example = "ArielMelhorCorretor@gmail.com", title = "E-mail")
    private String email;

    @Schema(description = "Telefone do Corretor", example = "(146) 99988-8888", title = "Telefone")
    private String telefone;

    @Schema(description = "Lista de Apólices do Corretor", title = "Apólices")
    private List<ApoliceDto> apolices;
}