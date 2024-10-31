package br.edu.famper.projetodeseguros.controller;

import br.edu.famper.projetodeseguros.dto.ApoliceDto;
import br.edu.famper.projetodeseguros.model.Apolice;
import br.edu.famper.projetodeseguros.service.ApoliceService;
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
@RequestMapping("api/apolice")
@RequiredArgsConstructor
@Slf4j
@Tag(name= "Apolice", description = "Operações relacionadas a apólices")
public class ApoliceController {

    private final ApoliceService apoliceService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obter todas as apólices do banco de dados",
            description = "Busca todas as apólices no banco e retorna em formato JSON")
    public List<ApoliceDto> getAllApolices() {
        log.info("Buscando todas as Apólices");
        return apoliceService.getAllApolices();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Apolice>> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(apoliceService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Apolice> create(@RequestBody Apolice apolice){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apoliceService.salvar(apolice));
    }

    @PutMapping
    public ResponseEntity<Apolice> update(@RequestBody Apolice apolice){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(apoliceService.update(apolice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        apoliceService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();}
}