package br.edu.famper.projetodeseguros.controller;

import br.edu.famper.projetodeseguros.dto.ApoliceDto;
import br.edu.famper.projetodeseguros.exception.ResourceNotFoundException;
import br.edu.famper.projetodeseguros.model.Apolice;
import br.edu.famper.projetodeseguros.service.ApoliceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/apolice")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Apolice", description = "Operações para apolices")
public class ApoliceController {

    private final ApoliceService apoliceService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todas as apolices", description = "Busca todas as apolices no banco de dados e retorna em formato JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public List<ApoliceDto> getAllApolices() {
        log.info("Buscando todas as apolices");
        return apoliceService.getAllApolices();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar apolice por ID", description = "Busca uma apolice específica no banco de dados e retorna em formato JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public ResponseEntity<ApoliceDto> getApoliceById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando apolice por id: {}", id);
        return ResponseEntity.ok().body(apoliceService.getApoliceById(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar apolice", description = "Cadastra uma nova apolice no banco de dados")
    public Apolice createApolice(@RequestBody ApoliceDto apoliceDto) throws ResourceNotFoundException {
        log.info("Cadastrando apolice: {}", apoliceDto);
        return apoliceService.saveApolice(apoliceDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar apolice", description = "Atualiza uma apolice específica no banco de dados")
    public ResponseEntity<ApoliceDto> updateApolice(@PathVariable(name = "id") Long id, @RequestBody ApoliceDto apoliceDto) throws ResourceNotFoundException {
        log.info("Atualizando apolice: {}", apoliceDto);
        return ResponseEntity.ok(apoliceService.editApolice(id, apoliceDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar apolice", description = "Deleta uma apolice específica no banco de dados")
    public Map<String, Boolean> deleteApolice(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando apolice: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", apoliceService.deleteApolice(id));
        return response;
    }
}

