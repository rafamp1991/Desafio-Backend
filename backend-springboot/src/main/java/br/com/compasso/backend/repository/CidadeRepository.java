package br.com.compasso.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.backend.model.CidadeModel;

public interface CidadeRepository extends JpaRepository<CidadeModel, Long> {
	
	CidadeModel findById(long id);
	
	CidadeModel findByNome(String nome);
	
	//CidadeModel findByEstado(String estado);
}
