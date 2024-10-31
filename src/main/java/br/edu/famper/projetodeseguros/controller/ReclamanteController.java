package br.edu.famper.projetodeseguros.controller;

import br.edu.famper.projetodeseguros.dto.ReclamanteDto;
import br.edu.famper.projetodeseguros.model.Reclamante;
import br.edu.famper.projetodeseguros.service.ReclamanteService;
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
@RequestMapping("api/reclamante")
@RequiredArgsConstructor
@Slf4j
@Tag(name= "Reclamante", description = "Operações relacionadas a reclamantes")
public class ReclamanteController {

    private final ReclamanteService reclamanteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obter todos os reclamantes do banco de dados", description = "Busca todos os reclamantes no banco e retorna em formato JSON")
    public List<ReclamanteDto> getAllReclamantes() {
        log.info("Buscando todos os Reclamantes");
        return reclamanteService.getAllReclamantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Reclamante>> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(reclamanteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Reclamante> create(@RequestBody Reclamante reclamante){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reclamanteService.salvar(reclamante));
    }

    @PutMapping
    public ResponseEntity<Reclamante> update(@RequestBody Reclamante reclamante){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(reclamanteService.update(reclamante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        reclamanteService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

