package br.com.compasso.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.compasso.backend.model.CidadeModel;

/**
 * Interface da cidade, que extende o framework Spring Data JPA.
 * De modo a disponibilizar funcionalidades pré-implantadas
 * que facilitam a consulta e persistência de dados.
 * @author Rafael Martins de Padua
 */
public interface CidadeRepository extends JpaRepository<CidadeModel, Long> {
	
	List<CidadeModel> findByNome(String nome);

	Optional<CidadeModel> findCidadeByNome(String nome);
}
