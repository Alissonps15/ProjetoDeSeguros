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

    private final CorretorRepository corretorRepository;


    public Corretor salvar(Corretor corretor) {
        return corretorRepository.save(corretor);
    }


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


    public Optional<Corretor> findById(Long id) {
        return corretorRepository.findById(id);
    }


    public Corretor update(Corretor corretor) {
        Corretor salvo = corretorRepository.findById(corretor.getId())
                .orElseThrow(() -> new RuntimeException("Corretor não encontrado"));
        salvo.setNome(corretor.getNome());
        salvo.setCnpj(corretor.getCnpj());
        salvo.setEmail(corretor.getEmail());
        salvo.setTelefone(corretor.getTelefone());
        return corretorRepository.save(salvo);
    }


    public void deleteById(Long id) {
        corretorRepository.deleteById(id);
    }
}