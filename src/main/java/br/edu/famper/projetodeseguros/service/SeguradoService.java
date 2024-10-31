package br.edu.famper.projetodeseguros.service;

import br.edu.famper.projetodeseguros.dto.SeguradoDto;
import br.edu.famper.projetodeseguros.model.Segurado;
import br.edu.famper.projetodeseguros.repository.SeguradoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeguradoService {

    private final SeguradoRepository seguradoRepository;


    public Segurado salvar(Segurado segurado) {
        return seguradoRepository.save(segurado);
    }


    public List<SeguradoDto> getAllSegurados() {
        return seguradoRepository
                .findAll()
                .stream()
                .map(segurado -> SeguradoDto
                        .builder()
                        .id(segurado.getId())
                        .nome(segurado.getNome())
                        .cpfCnpj(segurado.getCpfCnpj())
                        .endereco(segurado.getEndereco())
                        .telefone(segurado.getTelefone())
                        .email(segurado.getEmail())
                        .build())
                .toList();
    }


    public Optional<Segurado> findById(Long id) {
        return seguradoRepository.findById(id);
    }


    public Segurado update(Segurado segurado) {
        Segurado salvo = seguradoRepository.findById(segurado.getId())
                .orElseThrow(() -> new RuntimeException("Segurado não encontrado"));
        salvo.setNome(segurado.getNome());
        salvo.setCpfCnpj(segurado.getCpfCnpj());
        salvo.setEndereco(segurado.getEndereco());
        salvo.setTelefone(segurado.getTelefone());
        salvo.setEmail(segurado.getEmail());
        return seguradoRepository.save(salvo);
    }


    public void deleteById(Long id) {
        seguradoRepository.deleteById(id);
    }
}
