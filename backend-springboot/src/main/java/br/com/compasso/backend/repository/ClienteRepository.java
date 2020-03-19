package br.com.compasso.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compasso.backend.model.ClienteModel;

/**
 * Interface do cliente, que extende o framework Spring Data JPA.
 * De modo a disponibilizar funcionalidades pré-implantadas
 * que facilitam a consulta e persistência de dados.
 * @author Rafael Martins de Padua
 */
@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
	
	List<ClienteModel> findByNome(String nome);
	
	ClienteModel findByNomeAndSobrenome(String nome, String sobrenome);
}
