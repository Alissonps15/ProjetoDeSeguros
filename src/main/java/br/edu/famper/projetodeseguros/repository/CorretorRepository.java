package br.edu.famper.projetodeseguros.repository;

import br.edu.famper.projetodeseguros.model.Corretor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CorretorRepository extends JpaRepository<Corretor, Long> {
}