package br.com.compasso.backend.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.backend.model.CidadeModel;

public interface CidadeRepository extends JpaRepository<CidadeModel, Long> {
	
	CidadeModel findById(long id);
	
	CidadeModel findByNome(String nome);
	
	CidadeModel deleteById(long id);
}
