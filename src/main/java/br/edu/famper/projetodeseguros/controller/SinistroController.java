package br.edu.famper.projetodeseguros.controller;

import br.edu.famper.projetodeseguros.dto.SinistroDto;
import br.edu.famper.projetodeseguros.exception.ResourceNotFoundException;
import br.edu.famper.projetodeseguros.model.Sinistro;
import br.edu.famper.projetodeseguros.service.SinistroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sinistro")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Sinistro", description = "Operações para sinistros")
public class SinistroController {

    private final SinistroService sinistroService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todos os sinistros", description = "Busca todos os sinistros no banco de dados e retorna em formato JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public List<SinistroDto> getAllSinistros() {
        log.info("Buscando todos os sinistros");
        return sinistroService.getAllSinistros();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar sinistro por ID", description = "Busca um sinistro específico no banco de dados e retorna em formato JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public ResponseEntity<SinistroDto> getSinistroById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando sinistro por id: {}", id);
        return ResponseEntity.ok().body(sinistroService.getSinistroById(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar sinistro", description = "Cadastra um novo sinistro no banco de dados")
    public Sinistro createSinistro(@RequestBody SinistroDto sinistroDto) throws ResourceNotFoundException {
        log.info("Cadastrando sinistro: {}", sinistroDto);
        return sinistroService.saveSinistro(sinistroDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar sinistro", description = "Atualiza um sinistro específico no banco de dados")
    public ResponseEntity<SinistroDto> updateSinistro(@PathVariable(name = "id") Long id, @RequestBody SinistroDto sinistroDto) throws ResourceNotFoundException {
        log.info("Atualizando sinistro: {}", sinistroDto);
        return ResponseEntity.ok(sinistroService.editSinistro(id, sinistroDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar sinistro", description = "Deleta um sinistro específico no banco de dados")
    public Map<String, Boolean> deleteSinistro(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando sinistro: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", sinistroService.deleteSinistro(id));
        return response;
    }
}



