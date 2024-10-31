package br.edu.famper.projetodeseguros.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(title = "Reclamante", description = "Dados do Reclamante")
public class ReclamanteDto {

    @Schema(description = "ID do Reclamante", example = "1", title = "ID")
    private Long id;

    @Schema(description = "Nome do Reclamante", example = "José pica dura", title = "Nome")
    private String nome;

    @Schema(description = "CPF/CNPJ do Reclamante", example = "123.456.789-09", title = "CPF/CNPJ")
    private String cpfCnpj;

    @Schema(description = "Email do Reclamante", example = "JosePicadura@gmail.com", title = "email")
    private String email;

    @Schema(description = "Telefone do Reclamante", example = "(46) 99934-5678", title = "Telefone")
    private String telefone;
}
