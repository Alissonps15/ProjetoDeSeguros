package br.edu.famper.projetodeseguros.controller;

import br.edu.famper.projetodeseguros.dto.CorretorDto;
import br.edu.famper.projetodeseguros.model.Corretor;
import br.edu.famper.projetodeseguros.service.CorretorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/corretor")
@RequiredArgsConstructor
@Slf4j
@Tag(name= "Corretor", description = "Operações relacionadas a corretores")
public class CorretorController {

    private final CorretorService corretorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obter todos os corretores do banco de dados", description = "Busca todos os corretores no banco e retorna em formato JSON")
    public List<CorretorDto> getAllCorretores() {
        log.info("Buscando todos os Corretores");
        return corretorService.getAllCorretores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Corretor>> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(corretorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Corretor> create(@RequestBody Corretor corretor){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(corretorService.salvar(corretor));
    }

    @PutMapping
    public ResponseEntity<Corretor> update(@RequestBody Corretor corretor){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(corretorService.update(corretor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        corretorService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
