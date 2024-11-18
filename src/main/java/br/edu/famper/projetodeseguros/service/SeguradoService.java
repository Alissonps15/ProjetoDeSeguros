package br.edu.famper.projetodeseguros.service;

import br.edu.famper.projetodeseguros.dto.SeguradoDto;
import br.edu.famper.projetodeseguros.model.Segurado;
import br.edu.famper.projetodeseguros.repository.SeguradoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SeguradoService {
    @Autowired
    private final SeguradoRepository seguradoRepository;

    // Listar todos os segurados
    public List<SeguradoDto> getAllSegurados() {
        return seguradoRepository
                .findAll()
                .stream()
                .map(segurado -> SeguradoDto
                        .builder()
                        .id(segurado.getId())
                        .nome(segurado.getNome())
                        .cpfCnpj(segurado.getCpfCnpj())
                        .email(segurado.getEmail())
                        .telefone(segurado.getTelefone())
                        .build())
                .toList();
    }

    // Buscar um segurado
    public SeguradoDto getSeguradoById(Long id) {
        Segurado segurado = seguradoRepository.findById(id).orElseThrow();
        return SeguradoDto
                .builder()
                .id(segurado.getId())
                .nome(segurado.getNome())
                .cpfCnpj(segurado.getCpfCnpj())
                .email(segurado.getEmail())
                .telefone(segurado.getTelefone())
                .build();
    }

    // Salvar um segurado
    public Segurado saveSegurado(SeguradoDto seguradoDto) {
        Segurado segurado = new Segurado();
        segurado.setNome(seguradoDto.getNome());
        segurado.setCpfCnpj(seguradoDto.getCpfCnpj());
        segurado.setEmail(seguradoDto.getEmail());
        segurado.setTelefone(seguradoDto.getTelefone());
        return seguradoRepository.save(segurado);
    }

    // Editar um segurado
    public SeguradoDto editSegurado(Long id, SeguradoDto seguradoDto) {
        Segurado segurado = seguradoRepository.findById(id).orElseThrow();
        segurado.setNome(seguradoDto.getNome());
        segurado.setCpfCnpj(seguradoDto.getCpfCnpj());
        segurado.setEmail(seguradoDto.getEmail());
        segurado.setTelefone(seguradoDto.getTelefone());
        Segurado seguradoEdited = seguradoRepository.save(segurado);
        return SeguradoDto
                .builder()
                .id(seguradoEdited.getId())
                .nome(seguradoEdited.getNome())
                .cpfCnpj(seguradoEdited.getCpfCnpj())
                .email(seguradoEdited.getEmail())
                .telefone(seguradoEdited.getTelefone())
                .build();
    }

    // Deletar um segurado
    public boolean deleteSegurado(Long id) {
        try {
            Segurado segurado = seguradoRepository.findById(id).orElseThrow();
            seguradoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
