package br.edu.famper.projetodeseguros.controller;

import br.edu.famper.projetodeseguros.dto.SeguradoDto;
import br.edu.famper.projetodeseguros.exception.ResourceNotFoundException;
import br.edu.famper.projetodeseguros.model.Segurado;
import br.edu.famper.projetodeseguros.service.SeguradoService;
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
@RequestMapping("/api/segurado")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Segurado", description = "Operações para segurados")
public class SeguradoController {

    private final SeguradoService seguradoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todos os segurados", description = "Busca todos os segurado no banco de dados e retorna em formato JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public List<SeguradoDto> getAllSegurados() {
        log.info("Buscando todos os segurados");
        return seguradoService.getAllSegurados();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar segurado por ID", description = "Busca um segurado específico no banco de dados e retorna em formato JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public ResponseEntity<SeguradoDto> getSeguradoById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando segurado por id: {}", id);
        return ResponseEntity.ok().body(seguradoService.getSeguradoById(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar segurado", description = "Cadastra um novo segurado no banco de dados")
    public Segurado createSegurado(@RequestBody SeguradoDto seguradoDto) throws ResourceNotFoundException {
        log.info("Cadastrando segurado: {}", seguradoDto);
        return seguradoService.saveSegurado(seguradoDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar segurado", description = "Atualiza um segurado específico no banco de dados")
    public ResponseEntity<SeguradoDto> updateSegurado(@PathVariable(name = "id") Long id, @RequestBody SeguradoDto seguradoDto) throws ResourceNotFoundException {
        log.info("Atualizando segurado: {}", seguradoDto);
        return ResponseEntity.ok(seguradoService.editSegurado(id, seguradoDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar segurado", description = "Deleta um segurado específico no banco de dados")
    public Map<String, Boolean> deleteSegurado(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando segurado: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", seguradoService.deleteSegurado(id));
        return response;
    }
}

