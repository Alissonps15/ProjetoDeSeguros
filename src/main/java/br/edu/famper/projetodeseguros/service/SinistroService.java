package br.edu.famper.projetodeseguros.service;

import br.edu.famper.projetodeseguros.dto.SinistroDto;
import br.edu.famper.projetodeseguros.model.Sinistro;
import br.edu.famper.projetodeseguros.repository.SinistroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SinistroService {
    @Autowired
    private final SinistroRepository sinistroRepository;

    // Listar todos os sinistros
    public List<SinistroDto> getAllSinistros() {
        return sinistroRepository
                .findAll()
                .stream()
                .map(sinistro -> SinistroDto
                        .builder()
                        .id(sinistro.getId())
                        .descricao(sinistro.getDescricao())
                        .dataOcorrencia(sinistro.getDataOcorrencia())
                        .valorReclamado(sinistro.getValorReclamado())

                        .build())
                .toList();
    }

    // Buscar um sinistro
    public SinistroDto getSinistroById(Long id) {
        Sinistro sinistro = sinistroRepository.findById(id).orElseThrow();
        return SinistroDto
                .builder()
                .id(sinistro.getId())
                .descricao(sinistro.getDescricao())
                .dataOcorrencia(sinistro.getDataOcorrencia())
                .valorReclamado(sinistro.getValorReclamado())

                .build();
    }

    // Salvar um sinistro
    public Sinistro saveSinistro(SinistroDto sinistroDto) {
        Sinistro sinistro = new Sinistro();
        sinistro.setDescricao(sinistroDto.getDescricao());
        sinistro.setDataOcorrencia(sinistroDto.getDataOcorrencia());
        sinistro.setValorReclamado(sinistroDto.getValorReclamado());

        return sinistroRepository.save(sinistro);
    }

    // Editar um sinistro
    public SinistroDto editSinistro(Long id, SinistroDto sinistroDto) {
        Sinistro sinistro = sinistroRepository.findById(id).orElseThrow();
        sinistro.setDescricao(sinistroDto.getDescricao());
        sinistro.setDataOcorrencia(sinistroDto.getDataOcorrencia());
        sinistro.setValorReclamado(sinistroDto.getValorReclamado());

        Sinistro sinistroEdited = sinistroRepository.save(sinistro);
        return SinistroDto
                .builder()
                .id(sinistroEdited.getId())
                .descricao(sinistroEdited.getDescricao())
                .dataOcorrencia(sinistroEdited.getDataOcorrencia())
                .valorReclamado(sinistroEdited.getValorReclamado())

                .build();
    }

    // Deletar um sinistro
    public boolean deleteSinistro(Long id) {
        try {
            Sinistro sinistro = sinistroRepository.findById(id).orElseThrow();
            sinistroRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

