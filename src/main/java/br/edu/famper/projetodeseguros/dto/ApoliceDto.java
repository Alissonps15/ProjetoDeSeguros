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
@Schema(title = "Apolice    ", description = "Dados do Apólice")
public class ApoliceDto {

    @Schema(description = "ID do Apolice", example = "1", title = "ID")
    private Long id;

    @Schema(description = "Número da Apolice", example = "123456", title = "Número")
    private String numero;

    @Schema(description = "Data de Início", example = "2023-02-01", title = "Data Início")
    private LocalDate dataInicio;

    @Schema(description = "Data de Fim", example = "2024-05-01", title = "Data Fim")
    private LocalDate dataFim;

    @Schema(description = "Valor da Cobertura", example = "1000.00", title = "Valor Cobertura")
    private Double valorCobertura;

   @Schema(description = "Valor do Prêmio", example = "500.00", title = "Prêmio")
    private Double premio;

    @Schema(description = "Lista de Sinistros", title = "Sinistros")
    private List<SinistroDto> sinistros;
}