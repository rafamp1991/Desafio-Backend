package br.com.compasso.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.compasso.backend.model.CidadeModel;

public interface CidadeRepository extends JpaRepository<CidadeModel, Long> {
	
	List<CidadeModel> findByNome(String nome);

	Optional<CidadeModel> findCidadeByNome(String nome);
}
