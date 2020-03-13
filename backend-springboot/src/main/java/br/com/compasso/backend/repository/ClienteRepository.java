package br.com.compasso.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.compasso.backend.model.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
	
	List<ClienteModel> findByNome(String nome);
	
	ClienteModel findByNomeAndSobrenome(String nome, String sobrenome);
}
