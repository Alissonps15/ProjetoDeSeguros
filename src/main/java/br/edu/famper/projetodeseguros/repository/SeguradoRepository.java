package br.edu.famper.projetodeseguros.repository;


import br.edu.famper.projetodeseguros.model.Segurado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SeguradoRepository extends JpaRepository<Segurado, Long> {
}