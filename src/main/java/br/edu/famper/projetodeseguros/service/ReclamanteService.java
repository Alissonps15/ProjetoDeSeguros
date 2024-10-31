package br.edu.famper.projetodeseguros.service;

import br.edu.famper.projetodeseguros.dto.ReclamanteDto;
import br.edu.famper.projetodeseguros.model.Reclamante;
import br.edu.famper.projetodeseguros.repository.ReclamanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReclamanteService {

    private final ReclamanteRepository reclamanteRepository;


    public Reclamante salvar(Reclamante reclamante) {
        return reclamanteRepository.save(reclamante);
    }


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


    public Optional<Reclamante> findById(Long id) {
        return reclamanteRepository.findById(id);
    }


    public Reclamante update(Reclamante reclamante) {
        Reclamante salvo = reclamanteRepository.findById(reclamante.getId())
                .orElseThrow(() -> new RuntimeException("Reclamante não encontrado"));
        salvo.setNome(reclamante.getNome());
        salvo.setCpfCnpj(reclamante.getCpfCnpj());
        salvo.setEmail(reclamante.getEmail());
        salvo.setTelefone(reclamante.getTelefone());
        return reclamanteRepository.save(salvo);
    }


    public void deleteById(Long id) {
        reclamanteRepository.deleteById(id);
    }
}