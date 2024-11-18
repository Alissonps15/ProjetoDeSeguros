package br.edu.famper.projetodeseguros.service;

import br.edu.famper.projetodeseguros.dto.ReclamanteDto;
import br.edu.famper.projetodeseguros.model.Reclamante;
import br.edu.famper.projetodeseguros.repository.ReclamanteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReclamanteService {
    @Autowired
    private final ReclamanteRepository reclamanteRepository;

    // Listar todos os reclamantes
    public List<ReclamanteDto> getAllReclamantes() {
        return reclamanteRepository
                .findAll()
                .stream()
                .map(reclamante -> ReclamanteDto
                        .builder()
                        .id(reclamante.getId())
                        .nome(reclamante.getNome())
                        .cpfCnpj(reclamante.getCpfCnpj())
                        .email(reclamante.getEmail())
                        .telefone(reclamante.getTelefone())
                        .build())
                .toList();
    }

    // Buscar um reclamante
    public ReclamanteDto getReclamanteById(Long id) {
        Reclamante reclamante = reclamanteRepository.findById(id).orElseThrow();
        return ReclamanteDto
                .builder()
                .id(reclamante.getId())
                .nome(reclamante.getNome())
                .cpfCnpj(reclamante.getCpfCnpj())
                .email(reclamante.getEmail())
                .telefone(reclamante.getTelefone())
                .build();
    }

    // Salvar um reclamante
    public Reclamante saveReclamante(ReclamanteDto reclamanteDto) {
        Reclamante reclamante = new Reclamante();
        reclamante.setNome(reclamanteDto.getNome());
        reclamante.setCpfCnpj(reclamanteDto.getCpfCnpj());
        reclamante.setEmail(reclamanteDto.getEmail());
        reclamante.setTelefone(reclamanteDto.getTelefone());
        return reclamanteRepository.save(reclamante);
    }

    // Editar um reclamante
    public ReclamanteDto editReclamante(Long id, ReclamanteDto reclamanteDto) {
        Reclamante reclamante = reclamanteRepository.findById(id).orElseThrow();
        reclamante.setNome(reclamanteDto.getNome());
        reclamante.setCpfCnpj(reclamanteDto.getCpfCnpj());
        reclamante.setEmail(reclamanteDto.getEmail());
        reclamante.setTelefone(reclamanteDto.getTelefone());
        Reclamante reclamanteEdited = reclamanteRepository.save(reclamante);
        return ReclamanteDto
                .builder()
                .id(reclamanteEdited.getId())
                .nome(reclamanteEdited.getNome())
                .cpfCnpj(reclamanteEdited.getCpfCnpj())
                .email(reclamanteEdited.getEmail())
                .telefone(reclamanteEdited.getTelefone())
                .build();
    }

    // Deletar um reclamante
    public boolean deleteReclamante(Long id) {
        try {
            Reclamante reclamante = reclamanteRepository.findById(id).orElseThrow();
            reclamanteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
