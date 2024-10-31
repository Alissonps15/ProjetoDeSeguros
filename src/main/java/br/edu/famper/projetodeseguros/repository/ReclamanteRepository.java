package br.edu.famper.projetodeseguros.repository;

import br.edu.famper.projetodeseguros.model.Reclamante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ReclamanteRepository extends JpaRepository<Reclamante, Long> {
}