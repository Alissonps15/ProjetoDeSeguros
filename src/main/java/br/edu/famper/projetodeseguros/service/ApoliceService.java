package br.edu.famper.projetodeseguros.service;

import br.edu.famper.projetodeseguros.dto.ApoliceDto;
import br.edu.famper.projetodeseguros.dto.SinistroDto;
import br.edu.famper.projetodeseguros.model.Apolice;
import br.edu.famper.projetodeseguros.repository.ApoliceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApoliceService {

    @Autowired
    private final ApoliceRepository apoliceRepository;

    // Listar todas as apolices
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
                        .sinistros(apolice.getSinistros()
                                .stream()
                                .map(sinistro -> SinistroDto.builder()
                                        .id(sinistro.getId())
                                        .descricao(sinistro.getDescricao())
                                        .dataOcorrencia(sinistro.getDataOcorrencia())
                                        .valorReclamado(sinistro.getValorReclamado())
                                        .build())
                                .toList())
                        .build())
                .toList();
    }

    // Buscar uma apolice
    public ApoliceDto getApoliceById(Long id) {
        Apolice apolice = apoliceRepository.findById(id).orElseThrow();
        return ApoliceDto
                .builder()
                .id(apolice.getId())
                .numero(apolice.getNumero())
                .dataInicio(apolice.getDataInicio())
                .dataFim(apolice.getDataFim())
                .valorCobertura(apolice.getValorCobertura())
                .premio(apolice.getPremio())
                .build();
    }

    // Salvar uma apolice
    public Apolice saveApolice(ApoliceDto apoliceDto) {
        Apolice apolice = new Apolice();
        apolice.setNumero(apoliceDto.getNumero());
        apolice.setDataInicio(apoliceDto.getDataInicio());
        apolice.setDataFim(apoliceDto.getDataFim());
        apolice.setValorCobertura(apoliceDto.getValorCobertura());
        apolice.setPremio(apoliceDto.getPremio());
        apolice.setTipoSeguro(apoliceDto.getTipoSeguro());
        return apoliceRepository.save(apolice);
    }

    // Editar uma apolice
    public ApoliceDto editApolice(Long id, ApoliceDto apoliceDto) {
        Apolice apolice = apoliceRepository.findById(id).orElseThrow();
        apolice.setNumero(apoliceDto.getNumero());
        apolice.setDataInicio(apoliceDto.getDataInicio());
        apolice.setDataFim(apoliceDto.getDataFim());
        apolice.setValorCobertura(apoliceDto.getValorCobertura());
        apolice.setPremio(apoliceDto.getPremio());
        Apolice apoliceEdited = apoliceRepository.save(apolice);
        return ApoliceDto
                .builder()
                .id(apoliceEdited.getId())
                .numero(apoliceEdited.getNumero())
                .dataInicio(apoliceEdited.getDataInicio())
                .dataFim(apoliceEdited.getDataFim())
                .valorCobertura(apoliceEdited.getValorCobertura())
                .premio(apoliceEdited.getPremio())
                .build();
    }

    // Deletar uma apolice
    public boolean deleteApolice(Long id) {
        try {
            Apolice apolice = apoliceRepository.findById(id).orElseThrow();
            apoliceRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

