package br.edu.famper.projetodeseguros.controller;

import br.edu.famper.projetodeseguros.dto.ReclamanteDto;
import br.edu.famper.projetodeseguros.exception.ResourceNotFoundException;
import br.edu.famper.projetodeseguros.model.Reclamante;
import br.edu.famper.projetodeseguros.service.ReclamanteService;
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
@RequestMapping("/api/reclamante")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Reclamante", description = "Operações para reclamantes")
public class ReclamanteController {

    private final ReclamanteService reclamanteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todos os reclamantes", description = "Busca todos os reclamantes no banco de dados e retorna em formato JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public List<ReclamanteDto> getAllReclamantes() {
        log.info("Buscando todos os reclamantes");
        return reclamanteService.getAllReclamantes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar reclamante por ID", description = "Busca um reclamante específico no banco de dados e retorna em formato JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public ResponseEntity<ReclamanteDto> getReclamanteById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando reclamante por id: {}", id);
        return ResponseEntity.ok().body(reclamanteService.getReclamanteById(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar reclamante", description = "Cadastra um novo reclamante no banco de dados")
    public Reclamante createReclamante(@RequestBody ReclamanteDto reclamanteDto) throws ResourceNotFoundException {
        log.info("Cadastrando reclamante: {}", reclamanteDto);
        return reclamanteService.saveReclamante(reclamanteDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar reclamante", description = "Atualiza um reclamante específico no banco de dados")
    public ResponseEntity<ReclamanteDto> updateReclamante(@PathVariable(name = "id") Long id, @RequestBody ReclamanteDto reclamanteDto) throws ResourceNotFoundException {
        log.info("Atualizando reclamante: {}", reclamanteDto);
        return ResponseEntity.ok(reclamanteService.editReclamante(id, reclamanteDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar reclamante", description = "Deleta um reclamante específico no banco de dados")
    public Map<String, Boolean> deleteReclamante(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando reclamante: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", reclamanteService.deleteReclamante(id));
        return response;
    }
}


