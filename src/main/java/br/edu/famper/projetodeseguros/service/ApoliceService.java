package br.edu.famper.projetodeseguros.service;

import br.edu.famper.projetodeseguros.dto.ApoliceDto;
import br.edu.famper.projetodeseguros.model.Apolice;
import br.edu.famper.projetodeseguros.repository.ApoliceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApoliceService {

    private final ApoliceRepository apoliceRepository;


    public Apolice salvar(Apolice apolice) {
        return apoliceRepository.save(apolice);
    }


    public List<ApoliceDto> getAllApolices() {
        return apoliceRepository
                .findAll()
                .stream()
                .map(apolice -> ApoliceDto
                        .builder()
                        .id(apolice.getId())
                        .numero(apolice.getNumero())
                        .dataInicio(apolice.getDataInicio())
                        .dataFim(apolice.getDataFim())
                        .valorCobertura(apolice.getValorCobertura())
                        .premio(apolice.getPremio())
                        .build())
                .toList();
    }

    public Optional<Apolice> findById(Long id) {
        return apoliceRepository.findById(id);
    }

    public Apolice update(Apolice apolice) {
        Apolice salvo = apoliceRepository.findById(apolice.getId())
                .orElseThrow(() -> new RuntimeException("Apólice não encontrada"));
        salvo.setNumero(apolice.getNumero());
        salvo.setDataInicio(apolice.getDataInicio());
        salvo.setDataFim(apolice.getDataFim());
        salvo.setValorCobertura(apolice.getValorCobertura());
        salvo.setPremio(apolice.getPremio());
        return apoliceRepository.save(salvo);
    }

    public void deleteById(Long id) {
        apoliceRepository.deleteById(id);
    }
}
