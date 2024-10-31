
package br.edu.famper.projetodeseguros.controller;

import br.edu.famper.projetodeseguros.dto.SeguradoDto;
import br.edu.famper.projetodeseguros.model.Segurado;
import br.edu.famper.projetodeseguros.service.SeguradoService;
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
@RequestMapping("api/segurado")
@RequiredArgsConstructor
@Slf4j
@Tag(name= "Segurado", description = "Operações relacionadas a segurados")
public class SeguradoController {

    private final SeguradoService seguradoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obter todos os segurados do banco de dados", description = "Busca todos os segurados no banco e retorna em formato JSON")
    public List<SeguradoDto> getAllSegurados() {
        log.info("Buscando todos os Segurados");
        return seguradoService.getAllSegurados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Segurado>> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(seguradoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Segurado> create(@RequestBody Segurado segurado){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(seguradoService.salvar(segurado));
    }

    @PutMapping
    public ResponseEntity<Segurado> update(@RequestBody Segurado segurado){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(seguradoService.update(segurado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        seguradoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

