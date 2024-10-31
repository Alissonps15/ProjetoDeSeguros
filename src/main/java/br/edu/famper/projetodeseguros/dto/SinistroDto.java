package br.edu.famper.projetodeseguros.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(title = "Sinistro", description = "Dados do Sinistro")
public class SinistroDto {

    @Schema(description = "ID do Sinistro", example = "1", title = "ID")
    private Long id;

    @Schema(description = "Data da Ocorrência", example = "2022-01-01", title = "Data Ocorrência")
    private LocalDate dataOcorrencia;

    @Schema(description = "Descrição do Sinistro", example = "Carro veio deu pau", title = "Descrição")
    private String descricao;

    @Schema(description = "Valor Reclamado", example = "8500.00", title = "Valor Reclamado", minimum = "00,00")
    private Double valorReclamado;

    @Schema(description = "Lista de Reclamantes", title = "Reclamantes")
    private List<ReclamanteDto> reclamantes;
}