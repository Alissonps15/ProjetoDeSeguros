package br.edu.famper.projetodeseguros.controller;

import br.edu.famper.projetodeseguros.dto.CorretorDto;
import br.edu.famper.projetodeseguros.exception.ResourceNotFoundException;
import br.edu.famper.projetodeseguros.model.Corretor;
import br.edu.famper.projetodeseguros.service.CorretorService;
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
@RequestMapping("/api/corretor")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Corretor", description = "Operações para corretores")
public class CorretorController {

    private final CorretorService corretorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todos os corretores", description = "Busca todos os corretores no banco de dados e retorna em formato JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public List<CorretorDto> getAllCorretores() {
        log.info("Buscando todos os corretores");
        return corretorService.getAllCorretores();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar corretor por ID", description = "Busca um corretor específico no banco de dados e retorna em formato JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public ResponseEntity<CorretorDto> getCorretorById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando corretor por id: {}", id);
        return ResponseEntity.ok().body(corretorService.getCorretorById(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar corretor", description = "Cadastra um novo corretor no banco de dados")
    public Corretor createCorretor(@RequestBody CorretorDto corretorDto) throws ResourceNotFoundException {
        log.info("Cadastrando corretor: {}", corretorDto);
        return corretorService.saveCorretor(corretorDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar corretor", description = "Atualiza um corretor específico no banco de dados")
    public ResponseEntity<CorretorDto> updateCorretor(@PathVariable(name = "id") Long id, @RequestBody CorretorDto corretorDto) throws ResourceNotFoundException {
        log.info("Atualizando corretor: {}", corretorDto);
        return ResponseEntity.ok(corretorService.editCorretor(id, corretorDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar corretor", description = "Deleta um corretor específico no banco de dados")
    public Map<String, Boolean> deleteCorretor(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando corretor: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", corretorService.deleteCorretor(id));
        return response;
    }
}

