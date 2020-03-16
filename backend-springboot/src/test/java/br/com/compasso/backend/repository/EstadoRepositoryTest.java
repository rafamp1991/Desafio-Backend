package br.com.compasso.backend.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Optional;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.compasso.backend.model.EstadoModel;

@SpringBootTest
public class EstadoRepositoryTest {
	
	@Autowired
    private EstadoRepository estadoRepository;

	private EstadoModel estadoModel;
	
	@Before
    public void setUP() {
 
		estadoModel = new EstadoModel();
		estadoModel.setEstadoId(51L);
		estadoModel.setNome("Mato Grosso");
		estadoModel.setUf("MT");
    }
    
	@DisplayName("Teste para consultar o estado pelo Id")
    @Test
    public void findById(){
		Optional<EstadoModel> estado = estadoRepository.findById(51L);
		
		assertTrue(estado.isPresent());
		EstadoModel estadoModel = estado.get();
		assertEquals(estadoModel.getNome(), "Mato Grosso");
    }
	
	@DisplayName("Teste para consultar o estado pelo nome")
	@Test
	public void findByNome(){
		EstadoModel estado = estadoRepository.findByNome("Mato Grosso");
		assertEquals(estado.getEstadoId(), Long.valueOf(51));
	}
	
	@DisplayName("Teste para consultar o estado pelo uf")
	@Test
	public void findByuf(){
		EstadoModel estado = estadoRepository.findByUf("MT");
		assertEquals(estado.getEstadoId(), Long.valueOf(51));
	}
}
