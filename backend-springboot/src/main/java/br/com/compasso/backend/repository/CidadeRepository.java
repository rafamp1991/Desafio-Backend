package br.com.compasso.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.compasso.backend.model.CidadeModel;

public interface CidadeRepository extends JpaRepository<CidadeModel, Long> {
	
	CidadeModel findById(long cidadeId);
	
	CidadeModel findByNome(String nome);
	
	List<CidadeModel> findByEstadoId(long estadoId);
	
	CidadeModel deleteById(long cidadeId);
}
