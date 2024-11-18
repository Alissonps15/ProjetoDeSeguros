package br.edu.famper.projetodeseguros.service;

import br.edu.famper.projetodeseguros.dto.CorretorDto;
import br.edu.famper.projetodeseguros.model.Corretor;
import br.edu.famper.projetodeseguros.repository.CorretorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CorretorService {
    @Autowired
    private final CorretorRepository corretorRepository;

    // Listar todos os corretores
    public List<CorretorDto> getAllCorretores() {
        return corretorRepository
                .findAll()
                .stream()
                .map(corretor -> CorretorDto
                        .builder()
                        .id(corretor.getId())
                        .nome(corretor.getNome())
                        .cnpj(corretor.getCnpj())
                        .email(corretor.getEmail())
                        .telefone(corretor.getTelefone())
                        .build())
                .toList();
    }

    // Buscar um corretor
    public CorretorDto getCorretorById(Long id) {
        Corretor corretor = corretorRepository.findById(id).orElseThrow();
        return CorretorDto
                .builder()
                .id(corretor.getId())
                .nome(corretor.getNome())
                .cnpj(corretor.getCnpj())
                .email(corretor.getEmail())
                .telefone(corretor.getTelefone())
                .build();
    }

    // Salvar um corretor
    public Corretor saveCorretor(CorretorDto corretorDto) {
        Corretor corretor = new Corretor();
        corretor.setNome(corretorDto.getNome());
        corretor.setCnpj(corretorDto.getCnpj());
        corretor.setEmail(corretorDto.getEmail());
        corretor.setTelefone(corretorDto.getTelefone());
        return corretorRepository.save(corretor);
    }

    // Editar um corretor
    public CorretorDto editCorretor(Long id, CorretorDto corretorDto) {
        Corretor corretor = corretorRepository.findById(id).orElseThrow();
        corretor.setNome(corretorDto.getNome());
        corretor.setCnpj(corretorDto.getCnpj());
        corretor.setEmail(corretorDto.getEmail());
        corretor.setTelefone(corretorDto.getTelefone());
        Corretor corretorEdited = corretorRepository.save(corretor);
        return CorretorDto
                .builder()
                .id(corretorEdited.getId())
                .nome(corretorEdited.getNome())
                .cnpj(corretorEdited.getCnpj())
                .email(corretorEdited.getEmail())
                .telefone(corretorEdited.getTelefone())
                .build();
    }

    // Deletar um corretor
    public boolean deleteCorretor(Long id) {
        try {
            Corretor corretor = corretorRepository.findById(id).orElseThrow();
            corretorRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

