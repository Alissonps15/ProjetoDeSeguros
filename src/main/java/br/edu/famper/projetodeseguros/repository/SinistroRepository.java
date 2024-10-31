package br.edu.famper.projetodeseguros.repository;


import br.edu.famper.projetodeseguros.model.Sinistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SinistroRepository extends JpaRepository<Sinistro, Long> {
}