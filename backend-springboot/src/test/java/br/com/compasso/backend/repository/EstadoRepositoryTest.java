package br.com.compasso.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.compasso.backend.model.EstadoModel;

/**
 * @author Rafael Martins de Padua
 * @Repository
 */
@SpringBootTest
public class EstadoRepositoryTest {
	
	@Autowired
    private EstadoRepository estadoRepository;
    
	/**
	 * Teste para consultar o estado pelo Id
	 */
    @Test
    public void findById(){
		Optional<EstadoModel> estado = estadoRepository.findById(51L);
		
		assertTrue(estado.isPresent());
		EstadoModel estadoModel = estado.get();
		assertEquals(estadoModel.getNome(), "Mato Grosso");
    }
	
	/**
	 * Teste para consultar o estado pelo nome
	 */
	@Test
	public void findByNome(){
		EstadoModel estado = estadoRepository.findByNome("Mato Grosso");
		assertEquals(estado.getEstadoId(), Long.valueOf(51));
	}
	
	/**
	 * Teste para consultar o estado pelo uf
	 */
	@Test
	public void findByuf(){
		EstadoModel estado = estadoRepository.findByUf("MT");
		assertEquals(estado.getEstadoId(), Long.valueOf(51));
	}
}
