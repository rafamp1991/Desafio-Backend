package br.com.compasso.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compasso.backend.model.EstadoModel;

/**
 * Interface do estado, que extende o framework Spring Data JPA.
 * De modo a disponibilizar funcionalidades pré-implantadas
 * que facilitam a consulta e persistência de dados.
 * @author Rafael Martins de Padua
 */
@Repository
public interface EstadoRepository extends JpaRepository<EstadoModel, Long> {
	
	EstadoModel findByNome(String nome);
	
	EstadoModel findByUf(String uf);
}
