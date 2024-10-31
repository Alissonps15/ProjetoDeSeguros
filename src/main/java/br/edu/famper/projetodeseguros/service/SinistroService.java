package br.edu.famper.projetodeseguros.service;

import br.edu.famper.projetodeseguros.dto.ReclamanteDto;
import br.edu.famper.projetodeseguros.dto.SinistroDto;
import br.edu.famper.projetodeseguros.model.Sinistro;
import br.edu.famper.projetodeseguros.repository.SinistroRepository;
import br.edu.famper.projetodeseguros.repository.ApoliceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SinistroService {
    //https://www.youtube.com/watch?v=Eg-eOQfmrUE

    private final SinistroRepository sinistroRepository;
    private final ApoliceRepository apoliceRepository;


    public Sinistro salvar(Sinistro sinistro) {
        return sinistroRepository.save(sinistro);
    }


    public List<SinistroDto> getAllSinistros() {
        return sinistroRepository
                .findAll()
                .stream()
                .map(sinistro -> SinistroDto
                        .builder()
                        .id(sinistro.getId())
                        .dataOcorrencia(sinistro.getDataOcorrencia())
                        .descricao(sinistro.getDescricao())
                        .valorReclamado(sinistro.getValorReclamado())
                        .reclamantes(sinistro.getReclamantes()
                                .stream()
                                .map(reclamante -> ReclamanteDto
                                        .builder()
                                        .id(reclamante.getId())
                                        .nome(reclamante.getNome())
                                        .cpfCnpj(reclamante.getCpfCnpj())
                                        .build())
                                .toList())
                        .build())
                .toList();
    }


    public Optional<Sinistro> findById(Long id) {
        return sinistroRepository.findById(id);
    }


    public Sinistro update(Sinistro sinistro) {
        Sinistro salvo = sinistroRepository.findById(sinistro.getId())
                .orElseThrow(() -> new RuntimeException("Sinistro não encontrado"));
        salvo.setDataOcorrencia(sinistro.getDataOcorrencia());
        salvo.setDescricao(sinistro.getDescricao());
        salvo.setValorReclamado(sinistro.getValorReclamado());
        return sinistroRepository.save(salvo);
    }


    public void deleteById(Long id) {
        sinistroRepository.deleteById(id);
    }
}
