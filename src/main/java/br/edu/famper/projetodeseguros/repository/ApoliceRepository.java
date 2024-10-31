package br.edu.famper.projetodeseguros.repository;

import br.edu.famper.projetodeseguros.model.Apolice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ApoliceRepository extends JpaRepository<Apolice, Long> {

}