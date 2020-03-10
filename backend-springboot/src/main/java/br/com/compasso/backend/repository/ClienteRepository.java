package br.com.compasso.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.compasso.backend.model.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

	ClienteModel findById(long clienteId);
	
	ClienteModel findByNome(String nome);
	
	ClienteModel deleteById(long clienteId);
	
//	ClienteModel findbyNomeAndSobrenome(String nome, String sobrenome);
}
