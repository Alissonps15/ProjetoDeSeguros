package br.edu.famper.projetodeseguros.controller;

import br.edu.famper.projetodeseguros.dto.SinistroDto;
import br.edu.famper.projetodeseguros.model.Sinistro;
import br.edu.famper.projetodeseguros.service.SinistroService;
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
@RequestMapping("api/sinistro")
@RequiredArgsConstructor
@Slf4j
@Tag(name= "Sinistro", description = "Operações relacionadas a sinistros")
public class SinistroController {

    private final SinistroService sinistroService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obter todos os sinistros do banco de dados", description = "Busca todos os sinistros no banco e retorna em formato JSON")
    public List<SinistroDto> getAllSinistros() {
        log.info("Buscando todos os Sinistros");
        return sinistroService.getAllSinistros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Sinistro>> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(sinistroService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Sinistro> create(@RequestBody Sinistro sinistro){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sinistroService.salvar(sinistro));
    }

    @PutMapping
    public ResponseEntity<Sinistro> update(@RequestBody Sinistro sinistro){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(sinistroService.update(sinistro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        sinistroService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

